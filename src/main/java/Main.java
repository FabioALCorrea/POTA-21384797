
import java.util.*;
import java.io.File;
import java.lang.Exception;
import java.io.IOException;

public class Main {

    private static String clientes_FILENAME = "arquivoDados.csv";
    private static String KEY_ELEMENT_INDEX = "elementIndex";
    private static String KEY_COMPARISON_COUNT = "comparisonCount";

    public static void main(String[] args) throws IOException {
        Scanner opScanner = new Scanner(System.in);
        Scanner searchScanner = new Scanner(System.in);

        ArrayList<Cliente> clientes = readclientesFile();

        int op = 3;
        String search = "";

        while (op != 0) {
            System.out.println("\n1 - Visualizar clientes");
            System.out.println("2 - Buscar cliente");
            System.out.println("0 - Sair");
            op = opScanner.nextInt();

            if (op == 1) {
                displayclientes(clientes);
            } else if (op == 2) {
                System.out.print("Digite o nome do cliente que deseja procurar: ");
                search = searchScanner.nextLine();
                search = normalizeString(search);

                if (!search.contains("exit")) {
                    findElement(clientes, search);
                } else {
                    op = 0;
                }

            } else if (op != 0) {
                System.out.println("Opção inválida.");
            }
        }
    }

    public static ArrayList<Cliente> readclientesFile() {
        try {
            ArrayList<Cliente> clientes = new ArrayList<>();
            File file = new File(clientes_FILENAME);
            Scanner scanner = new Scanner(file);

            // enquanto não chegar ao final do arquivo
            while (scanner.hasNextLine()) {
                // leia a próxima linha do arquivo
                String linha = scanner.nextLine();

                // quebrando a linha por ","
                String[] partes = linha.split(",");

                // adicionado no array cada cliente
                int age = 0;
                if (partes[6].matches("\\d+")) {
                    age = java.lang.Integer.parseInt(partes[6]);
                }

                Cliente cliente = new Cliente(normalizeString(partes[0]), partes[1], partes[2], partes[3], partes[4], partes[5], age);
                clientes.add(cliente);
            }

            scanner.close();

            return clientes;
        } catch (Exception ex) {
            return new ArrayList<Cliente>();
        }
    }

    public static void findElement(ArrayList<Cliente> clientes, String search) {
        Map<String, Integer> result = recursiveBinarySearch(clientes, 0, clientes.size() - 1, search, 0);

        Integer elementIndex = result.get(KEY_ELEMENT_INDEX);
        Integer comparisonCount = result.get(KEY_COMPARISON_COUNT);

        if (elementIndex != -1) {
            System.out.println();
            clientes.get(elementIndex).prettyPrint();
            System.out.println();
            System.out.println("Index do elemento: " + elementIndex);
            System.out.println("Quantidade de comparações: " + comparisonCount);
            System.out.println();
        } else {
            System.out.println();
            System.out.println("Elemento não encontrado.");
            System.out.println("Quantidade de comparações: " + comparisonCount);
        }
    }

    public static Map<String, Integer> recursiveBinarySearch(ArrayList<Cliente> clientes, int start, int end, String search, int count) {
        int mid = start + (end - start) / 2;

        if (mid > clientes.size() - 1
                || mid < 0
                || (mid == 0 && clientes.get(mid).nome.compareTo(search) != 0)
                || (mid == clientes.size() && clientes.get(mid).nome.compareTo(search) != 0)) {
            return makeResult(-1, count + 1);
        }

        if (clientes.get(mid).nome.compareTo(search) == 0) {
            return makeResult(mid, count + 1);
        }

        if (clientes.get(mid).nome.compareTo(search) > 0) {
            return recursiveBinarySearch(clientes, start, mid - 1, search, count + 1);
        } else if (clientes.get(mid).nome.compareTo(search) < 0) {
            return recursiveBinarySearch(clientes, mid + 1, end, search, count + 1);
        }

        return makeResult(-1, count + 1);
    }

    public static Map<String, Integer> makeResult(int elementIndex, int count) {
        Map<String, Integer> result = new HashMap();
        result.put(KEY_ELEMENT_INDEX, elementIndex);
        result.put(KEY_COMPARISON_COUNT, count);
        return result;
    }

    public static void displayclientes(ArrayList<Cliente> clientes) {
        for (Cliente client : clientes) {
            System.out.println(client);
        }
    }

    public static String normalizeString(String str) {
        String normalized = java.text.Normalizer.normalize(str, java.text.Normalizer.Form.NFD);
        normalized = normalized.replaceAll("[^\\p{ASCII}]", "");
        return normalized;

        /**
         * Erik Felintro Kamite RA:21420243 William Roberto da silva RA:21384797
         * Juliana Silva e Souza RA:21434583 Fábio Augusto de lima Corrêa
         * RA:21427394
         */
    }

}
