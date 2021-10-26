import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        gerarArquivo(gerarExpressao());
    }

    public static int lerRepeticao() throws IOException {
        Path caminho = Paths.get("src\\readme.txt");
        List<String> lst = Files.readAllLines(caminho, StandardCharsets.UTF_8);
        Integer repeticoes = 0;
        for (String string : lst) {
            Integer inicio = string.indexOf('n');
            Integer iniValor = string.indexOf(':') + 1;
            if (inicio == 0) {
                repeticoes = Integer.parseInt(string.substring(iniValor, string.length()));
            }
        }
        return repeticoes;
    }

    public static String lerExpressao() throws IOException {
        Path caminho = Paths.get("src\\readme.txt");
        List<String> lst = Files.readAllLines(caminho, StandardCharsets.UTF_8);
        String expressao = "";
        for (String string : lst) {
            Integer inicio = string.indexOf("exp");
            Integer iniValor = string.indexOf(":") + 1;
            if (inicio == 0) {
                expressao = string.substring(iniValor, string.length());
            }
        }
        return expressao;
    }

    public static String lerRegra1() throws IOException {
        Path caminho = Paths.get("src\\readme.txt");
        List<String> lst = Files.readAllLines(caminho, StandardCharsets.UTF_8);
        String regra = "";
        for (String string : lst) {
            Integer inicio = string.indexOf("p1");
            Integer iniValor = string.indexOf(':') + 1;
            if (inicio == 0) {
                regra = string.substring(iniValor, string.length());
            }
        }
        return regra;
    }

    public static String lerRegra2() throws IOException {
        Path caminho = Paths.get("src\\readme.txt");
        List<String> lst = Files.readAllLines(caminho, StandardCharsets.UTF_8);
        String regra = "";
        for (String string : lst) {
            Integer inicio = string.indexOf("p2");
            Integer iniValor = string.indexOf(':') + 1;
            if (inicio == 0) {
                regra = string.substring(iniValor, string.length());
            }
        }
        return regra;
    }

    public static String gerarExpressao() throws IOException {

        Integer repecicoes = lerRepeticao();
        String expressao = lerExpressao();
        String regraF = lerRegra1();
        String regraf = lerRegra2();
        String expressaoGeral = "";
        String aux = expressao;

        if (repecicoes != 0 && expressao != "" && regraF != "" & regraf != "") {
            for (int x = 0; x < repecicoes; x++) {
                expressao = aux;
                aux = "";
                for (int y = 0; y < expressao.length(); y++) {
                    char d = expressao.charAt(y);
                    if (d == 'F') {
                        aux += regraF;
                    } else if (d == 'f') {
                        aux += regraf;
                    } else {
                        aux += d;
                    }
                }

            }
            expressaoGeral = aux;
        }

        return expressaoGeral;
    }

    public static void gerarArquivo(String expressaoGeral) throws IOException {

        FileWriter arq = new FileWriter("src//desenho.html");
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.println("<!DOCTYPE html>");
        gravarArq.println("<html>");
        gravarArq.println("<style>");
        gravarArq.println("body\n{");
        gravarArq.println("background-color:black;");
        gravarArq.println("}");
        gravarArq.println("</style>");
        gravarArq.println("<head>");
        gravarArq.println("</head>");
        gravarArq.println("<body>");
        gravarArq.println("<svg height='10000' width='10000'>");
        

        Integer anguloAtual = 0;
        Integer direcaoX = 1;
        Integer direcaoY = 1;
        Boolean hor = true;
        Integer iniX = 700;
        Integer iniY = 600;

        for (int i = 0; i < expressaoGeral.length(); i++) {
            char c = expressaoGeral.charAt(i);

            if (c == '+') {
                anguloAtual += 90;
                if (anguloAtual == 360)
                    anguloAtual = 0;

                
            }

            else if (c == '-') {
                anguloAtual -= 90;
                if (anguloAtual < 0)
                    anguloAtual = 270;

                
            }

            else if (c == 'F') {
                if (hor) {
                    gravarArq.println("<line x1='" + iniX + "' y1=" + iniY + " x2=" + (iniX + (10 * direcaoX)) + " y2="
                            + iniY + " style='stroke:rgb(255,0,0);stroke-width:2' />");
                    iniX += (10 * direcaoX);
                } else {
                    gravarArq.println("<line x1='" + iniX + "' y1=" + iniY + " x2=" + iniX + " y2="
                            + (iniY + (10 * direcaoY)) + " style='stroke:rgb(255,255,255);stroke-width:2' />");
                    iniY += (10 * direcaoY);
                }
            }

            else if (c == 'f') {
                if (hor) {
                    iniX += (10 * direcaoX);
                } else {

                    iniY += (10 * direcaoY);
                }
            }

            if (anguloAtual == 0) {
                direcaoX = 1;
                direcaoY = -1;
                hor = true;
            } else if (anguloAtual == 90) {
                direcaoX = -1;
                direcaoY = -1;
                hor = false;

            } else if (anguloAtual == 180) {
                direcaoX = -1;
                direcaoY = 1;
                hor = true;

            } else if (anguloAtual == 270) {
                direcaoX = 1;
                direcaoY = 1;
                hor = false;
            }

        }

        gravarArq.println("</svg>");
        gravarArq.println("</body>");
        gravarArq.println("</html>");
        arq.close();

    }
}
