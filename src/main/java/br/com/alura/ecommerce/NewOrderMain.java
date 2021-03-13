package br.com.alura.ecommerce;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain  {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        try ( var dispatcher = new KafkaDisppatcher() ) {
            ;

            for (var i = 0; i < 10; i++) {

                var key = UUID.randomUUID().toString();

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                var value = key + " - " + dtf.format(now);


                dispatcher.send("ECOMMERCE_NEW_ORDER", key, value);


                var email = "Thank you, we are currently processing your order!";
                dispatcher.send("ECOMMERCE_SEND_EMAIL", key, email);

            }
        }





    }




}
