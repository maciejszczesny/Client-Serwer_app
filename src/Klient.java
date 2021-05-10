import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class Klient {
    public static final int PORT = 10000;
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) throws IOException {
                 VmAlphaSetOfNumbers clientNumbers = new VmAlphaSetOfNumbers();
                 Set<Integer> clientNumbersValue = clientNumbers.getGeneratedNumbers();
                 List<Integer> mainListClient = new ArrayList<>();
                 mainListClient.addAll(clientNumbersValue);
                 String listString = mainListClient.stream().map(Object::toString).collect(Collectors.joining(","));

                 //nawiazanie polaczenia z serwerem
                Socket sock = new Socket(HOST, PORT);
                System.out.println("Nawiazalem polaczenie: " + sock);



        //tworzenie strumieni danych i dostarczanie do socketu
         BufferedReader klaw = new BufferedReader(new InputStreamReader(System.in));
         PrintWriter outp = new PrintWriter(sock.getOutputStream());
        outp.println(listString);
        outp.flush();


        //tworzenie strumienia danych pobieranych z gniazda sieciowego
        BufferedReader inp = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        //Odczyt danych ze strumienia
        int correctHits;
        correctHits = inp.read();
        System.out.println("Liczba prawidlowych trafien " + correctHits);


        try {
            FileWriter myWriter = new FileWriter("wygrane.txt", true);
            myWriter.write("Ilosc trafien "+correctHits+ " dla zestawu liczb "+ listString);
            myWriter.write(System.getProperty("line.separator"));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //dataOutputStream.close();
            //zamykanie polaczenia

            sock.close();
    }
}

