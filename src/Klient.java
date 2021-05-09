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
            //for (int i = 0; i < 5; i++) {
                 VmAlphaSetOfNumbers clientNumbers = new VmAlphaSetOfNumbers();
                 Set<Integer> clientNumbersValue = clientNumbers.getGeneratedNumbers();
                 List<Integer> mainListClient = new ArrayList<>();
                 mainListClient.addAll(clientNumbersValue);
                //String [] clientArrayNumbers = mainListClient.toArray(String []::new);
                String listString = mainListClient.stream().map(Object::toString).collect(Collectors.joining(","));


                 //nawiazanie polaczenia z serwerem
                Socket sock = new Socket(HOST, PORT);
                System.out.println("Nawiazalem polaczenie: " + sock);

                //tworzenie strumieni danych i dostarczanie do socketu
               // BufferedReader klaw = new BufferedReader(new InputStreamReader(System.in));
               // PrintWriter outp = new PrintWriter(sock.getOutputStream());

                OutputStream outputStream = sock.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.writeBytes(listString);
                dataOutputStream.flush();
                dataOutputStream.close();
                //przekazywanie danych  do strumienia
               // System.out.print("Podaj liczby do skreslenia : ");
                //String str = klaw.readLine();
                //outp.println(str);
                //outp.flush();


        //tworzenie strumienia danych pobieranych z gniazda sieciowego
        BufferedReader inp = new BufferedReader(new InputStreamReader(sock.getInputStream()));

        //Odczyt danych ze strumienia
        int correctHits;
        correctHits = inp.read();
        System.out.println("Liczba prawidlowych trafien " + correctHits);



        try {
            FileWriter myWriter = new FileWriter("wygrane.txt", true);
            myWriter.write("Ilosc trafien "+correctHits+ " dla zestawu liczb "+ mainListClient);
            myWriter.write(System.getProperty("line.separator"));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

            //zamykanie polaczenia
            //klaw.close();
           // outp.close();
           // sock.close();
    }
}

