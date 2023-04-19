package web;

import models.Horario;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static services.HorarioCsvReader.processCsvStream;
import static services.HorarioJSONReader.processJsonStream;

/**
 * Servlet responsável por receber e processar ficheiros CSV ou JSON contendo dados de horários universitários.
 * Os ficheiros podem ser enviados através de um formulário HTML com um campo de ‘upload’ de ficheiros ou por uma URL.
 * Caso o ficheiro seja enviado através de um URL, é necessário especificar um URL válido no campo de ‘upload’.
 * Quando um ficheiro é recebido e processado, os horários contidos no ficheiro são armazenados numa sessão HTTP,
 * que pode ser acedida por outras partes da aplicação.
 */
public class UploadServlet extends HttpServlet {
    public static final String HORARIOS_SESSION = "horarios";

    /**
     * Recebe um pedido HTTP POST enviado pelo utilizador para fazer ‘upload’ de um ficheiro CSV ou JSON.
     * <p>
     * Verifica se o ‘upload’ é de um ficheiro local ou de um URL.
     * Se for um ficheiro local, o ficheiro é processado pela função processFile.
     * Se for um URL, o ficheiro é processado pela função processURL.
     * Define a mensagem de sucesso/erro na sessão do utilizador e redireciona-o para a página de ‘upload’.
     *
     * @param request  O objeto HttpServletRequest que contém as informações sobre a solicitação HTTP feita pelo utilizador.
     * @param response O objeto HttpServletResponse que contém as informações da resposta HTTP enviada de volta para o utilizador.
     * @throws IOException Caso ocorra um erro durante o processamento do ficheiro ou da URL.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Limpa a variavel de sessão que contem a lista de horarios
        request.getSession().removeAttribute(HORARIOS_SESSION);
        
        // Obtém a URL inserida pelo utilizador (se houver)
        String url = request.getParameter("url");
        String message;
        
        // Se não houver URL, o ficheiro é local sendo processado pela função processFile
        if (url == null) { // ficheiro local
            message = processFile(request);
        } else {
            // Se houver URL, o ficheiro é processado pela função processURL
            message = processURL(request);
        }
        // Define a mensagem de sucesso/erro na sessão do utilizador e redireciona-o para a página de ‘upload’
        request.getSession().setAttribute("messageUpload", message);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    /**
     * Processa o ficheiro a partir de uma URL. A URL é obtida a partir do parâmetro "url" da solicitação HTTP.
     * <p>
     * Primeiro, é feita uma verificação para garantir que a URL seja válida. Em seguida, é aberta uma conexão para
     * a URL sendo verificado se o código de resposta HTTP está na faixa de 200 (indicando sucesso). Caso contrário,
     * retorna uma mensagem de erro indicando que houve um erro ao fazer o ‘download’ do ficheiro da URL.
     * Em seguida, o tipo de ficheiro é determinado a partir da extensão do ficheiro e o ficheiro é processado pela função
     * processCsvOrJsonFile(), que retorna uma mensagem indicando se o ficheiro foi importado com sucesso ou não.
     *
     * @param request O objeto HttpServletRequest que contém a solicitação HTTP enviada pelo cliente.
     * @return Uma mensagem indicando se o ficheiro foi importado com sucesso ou uma mensagem de erro, caso contrário.
     */
    private String processURL(HttpServletRequest request) {
        // Obtém a URL inserida pelo utilizador a partir do parâmetro "url" da requisição HTTP
        String url = request.getParameter("url");
        try {
            // Cria um objeto URL a partir da ‘string’ da URL obtida acima
            URL downloadUrl = new URL(url);
            // Abre uma conexão HTTP para a URL
            HttpURLConnection connection = (HttpURLConnection) downloadUrl.openConnection();
            // Verifica se a resposta da requisição HTTP possui código de sucesso (no caso, 200-299)
            if (connection.getResponseCode() / 100 != 2) {
                return "Error downloading file from URL";
            }
            // Extrai a extensão do ficheiro a partir da URL
            String fileExtension = getFileExtension(connection.getURL().toString());
            // Processa o ficheiro (que pode ser CSV ou JSON) utilizando a função processCsvOrJsonFile,
            // passando como parâmetros o InputStream, a extensão do ficheiro e a requisição HTTP
            return processCsvOrJsonFile(connection.getInputStream(), fileExtension, request);

        } catch (MalformedURLException e) {
            // Retorna uma mensagem de erro caso a URL seja inválida
            return "URL invalido";
        } catch (IOException | CsvValidationException e) {
            // Retorna uma mensagem de erro caso ocorra alguma exceção ao fazer o ‘download’ do ficheiro
            return "Erro ao fazer download do ficheiro do URL: " + e.getMessage();
        }
    }

    /**
     * Processa um ficheiro enviado por um pedido HTTP e atualiza a sessão com os dados contidos no ficheiro.
     * <p>
     * Este método verifica se o tipo de conteúdo do pedido é do tipo "multipart/", o que indica haver um ficheiro a ser enviado.
     * Em seguida, verifica se o ficheiro é um ficheiro CSV ou JSON. Se o ficheiro for um CSV, o método processCsvFile é chamado para
     * importar os dados do ficheiro para a sessão. Se for um ficheiro JSON, o método processJsonFile é chamado para o mesmo fim.
     * Se o tipo de ficheiro não for suportado, uma mensagem de erro é retornada.
     *
     * @param request O pedido HTTP que contém o ficheiro.
     * @return Uma mensagem indicando se o ficheiro foi importado com sucesso ou se algum erro ocorreu.
     */
    private String processFile(HttpServletRequest request) {
        final String MULTIPART_TYPE = "multipart/";
        String contentType = request.getContentType();

        if (contentType != null && contentType.startsWith(MULTIPART_TYPE)) {
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            try {
                List<FileItem> items = upload.parseRequest(request);
                for (FileItem item : items) {
                    if (!item.isFormField()) {
                        String fileExtension = getFileExtension(item.getName());
                        return processCsvOrJsonFile(item.getInputStream(), fileExtension, request);
                    }
                }
                return "Os ficheiros selecionados não são suportados. Por favor escolha CSV ou JSON";
            } catch (IOException | CsvValidationException | FileUploadException e) {
                return "Erro ao importar o ficheiro: " + e.getMessage();
            }
        }
        return "Sem ficheiro válido";

    }

    /**
     * Processa um ficheiro CSV ou JSON, dependendo da extensão do ficheiro.
     * <p>
     * Se a extensão do ficheiro for CSV, chama a função processCsvFile() para processar o ficheiro.
     * Se a extensão do ficheiro for JSON, chama a função processJsonFile() para processar o ficheiro.
     * Se a extensão do ficheiro não for nem CSV, nem JSON, retorna uma mensagem de erro.
     *
     * @param inputStream   o InputStream que contém o conteúdo do ficheiro a ser processado
     * @param fileExtension a extensão do ficheiro a ser processado (pode ser ".csv" ou ".json")
     * @param request       o HttpServletRequest associado ao pedido HTTP que originou o envio do ficheiro
     * @return uma mensagem de erro ou a mensagem de sucesso da função processCsvFile() ou processJsonFile()
     * @throws IOException            se ocorrer um erro ao ler o conteúdo do InputStream
     * @throws CsvValidationException se ocorrer um erro ao validar o ficheiro CSV
     */
    private String processCsvOrJsonFile(InputStream inputStream, String fileExtension, HttpServletRequest request) throws IOException, CsvValidationException {
        // Define as constantes para as extensões de ficheiro suportadas
        String csvExtension = ".csv";
        String jsonExtension = ".json";

        // Verifica a extensão do ficheiro e processa o ficheiro correspondente
        if (fileExtension.equalsIgnoreCase(csvExtension)) {
            return processCsvFile(inputStream, request);
        } else if (fileExtension.equalsIgnoreCase(jsonExtension)) {
            return processJsonFile(inputStream, request);
        } else {
            // Se a extensão do ficheiro não for suportada, retorna uma mensagem de erro
            return "Os ficheiros selecionados não são suportados. Por favor escolha CSV ou JSON";
        }
    }

    /**
     * Obtém a extensão do ficheiro a partir do nome do ficheiro.
     *
     * @param fileName o nome do ficheiro do qual se pretende obter a extensão.
     * @return a extensão do ficheiro, se existir, caso contrário retorna uma ‘string’ vazia.
     */
    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        return lastDotIndex != -1 ? fileName.substring(lastDotIndex) : "";        
    }

    /**
     * Processa um ficheiro CSV contendo informações de horários e armazena os dados processados na sessão HTTP.
     *
     * @param item    o InputStream do ficheiro CSV a ser processado.
     * @param request o objeto HttpServletRequest associado à solicitação HTTP.
     * @return uma mensagem indicando que o ficheiro CSV foi importado com sucesso, ou uma mensagem de erro caso ocorra uma exceção.
     * @throws IOException            se ocorrer um erro de E/S durante a leitura do ficheiro CSV.
     * @throws CsvValidationException se ocorrer um erro de validação do ficheiro CSV.
     */
    private String processCsvFile(InputStream item, HttpServletRequest request) throws
            IOException, CsvValidationException {
        // Processa o CSV utilizando o método processCsvStream e armazena o resultado na lista schedule
        List<Horario> schedule = processCsvStream(item);
        // Armazena a lista schedule na sessão HTTP com o nome "horarios".
        request.getSession().setAttribute(HORARIOS_SESSION, schedule);
        // Retorna uma mensagem indicando que o ficheiro CSV foi importado com sucesso.
        return "Ficheiro CSV importado com sucesso.";
    }

    /**
     * Processa um ficheiro JSON contendo informações de horários e armazena os dados processados na sessão HTTP.
     *
     * @param item    o InputStream do ficheiro CSV a ser processado.
     * @param request o objeto HttpServletRequest associado à solicitação HTTP.
     * @return uma mensagem indicando que o ficheiro JSON foi importado com sucesso, ou uma mensagem de erro caso ocorra uma exceção.
     * @throws IOException se ocorrer um erro de E/S durante a leitura do ficheiro JSON.
     */
    private String processJsonFile(InputStream item, HttpServletRequest request) throws IOException {
        // Processa o JSON utilizando o método processJsonStream e armazena o resultado na lista schedule
        List<Horario> schedule = processJsonStream(item);
        // Armazena a lista schedule na sessão HTTP com o nome "horarios".
        request.getSession().setAttribute(HORARIOS_SESSION, schedule);
        // Retorna uma mensagem indicando que o ficheiro JSON foi importado com sucesso.
        return "Ficheiro JSON importado com sucesso.";
    }
}