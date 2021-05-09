import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class Serwer
{
    public static final int PORT=10000;

    public static void main(String args[]) throws IOException
    {
            VmAlphaSetOfNumbers serwerNumbers = new VmAlphaSetOfNumbers();
            Set<Integer> serwerNumbersValue = serwerNumbers.getGeneratedNumbers();
            for (int i = 0; i < 5; i++) {
                //tworzenie gniazda serwerowego
                ServerSocket serv = new ServerSocket(PORT);

                //oczekiwanie na polaczenie i tworzenie gniazda sieciowego
                System.out.println("Nasluchuje: " + serv);
                Socket sock = serv.accept();
                System.out.println("Jest polaczenie: " + sock);

                //tworzenie strumienia danych pobieranych z gniazda sieciowego
                BufferedReader inp = new BufferedReader(new InputStreamReader(sock.getInputStream()));

                //Odczyt danych ze strumienia
                String clientNumbersAlpha;
                clientNumbersAlpha = inp.readLine();
                System.out.println("Liczby maszyny Bravo (gracz) " + clientNumbersAlpha);
                List<String> clientList = List.of(clientNumbersAlpha.split(","));

                // Client list of numbers
                String[] convertedClientArray = clientNumbersAlpha.split(",");
                List<Integer> convertedClientList = new ArrayList<Integer>();
                    for (String number : convertedClientArray)
                    {
                      convertedClientList.add(Integer.parseInt(number.trim()));
                    }

                // Serwer list of numbers
                List<Integer> mainList = new ArrayList<>();
                mainList.addAll(serwerNumbersValue);
                System.out.println("List of integers from server " + mainList);
                mainList.retainAll(convertedClientList);
                List<Integer> commonNumbers = mainList;
                // Comparing two lists
                System.out.println("Lista podobnych liczb "+commonNumbers);
                Integer numberOfHits = commonNumbers.size();
                //valid deletions
                System.out.println("Liczba trafień " + numberOfHits +" /6");



    /*            //Integer stringNumberOfHits = numberOfHits;
                String stringNumberOfHits = numberOfHits.toString();

                //InputStream klaw2 = new ByteArrayInputStream(stringNumberOfHits.getBytes(StandardCharsets.UTF_8));
                InputStream klaw2 = sock.getInputStream();

                PrintWriter outp = new PrintWriter(sock.getOutputStream());

                //przekazywanie danych  do strumienia
                System.out.print("ilosc dobrych skreslen : ");
                //String str = klaw.readLine();
                outp.println(klaw2);
                outp.flush();*/

                OutputStream outputStream = sock.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.write(numberOfHits);
                dataOutputStream.flush();
                dataOutputStream.close();


                //zamykanie polaczenia
                inp.close();
                sock.close();
                serv.close();
        }

    }
}