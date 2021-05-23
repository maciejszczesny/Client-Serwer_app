import java.io.*;
import java.net.*;
import java.util.*;


public class Serwer
{
    public static final int PORT=10000;

    public static void main(String args[]) throws IOException
    {
        VmAlphaSetOfNumbers serwerNumbers = new VmAlphaSetOfNumbers();
        Set<Integer> serwerNumbersValue = serwerNumbers.getGeneratedNumbers();
        ServerSocket serv = new ServerSocket(PORT);

        for (int i = 0; i < 10000; i++) {

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
            //List<String> clientList = List.of(clientNumbersAlpha.split(","));

            // Client list of numbers
            String[] convertedClientArray = clientNumbersAlpha.split(",");
            List<Integer> convertedClientList = new ArrayList<Integer>();
            for (String number : convertedClientArray)
            {
                convertedClientList.add(Integer.parseInt(number.trim()));
            }

            // Liczby po stronie serwera
            List<Integer> mainList = new ArrayList<>();
            mainList.addAll(serwerNumbersValue);
            System.out.println("Zwycięskie liczby " + mainList);
            mainList.retainAll(convertedClientList);
            List<Integer> commonNumbers = mainList;
            // Porownywanie list
            System.out.println("Lista trafionych liczb "+commonNumbers);
            Integer numberOfHits = commonNumbers.size();
            System.out.println("Liczba trafień " + numberOfHits +"/6");

            // Wysylanie poprzez strumien danych informacji o ilosci trafien
            OutputStream outputStream = sock.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.write(numberOfHits);
            dataOutputStream.flush();


            //closing conection
//                inp.close();
//                sock.close();
//                serv.close();
        }

    }
}



//Sending information about numberOfHits via stream
