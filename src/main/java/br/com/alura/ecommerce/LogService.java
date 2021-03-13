package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import java.util.regex.Pattern;

public class LogService {

    public static void main(String[] args) {

        var logService = new LogService();
        var service = new KafkaService( LogService.class.getSimpleName() , Pattern.compile("ECOMMERCE.*").toString(),
                logService::parse );
        service.run();

    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("-----------------------------------------");
        System.out.println("LOG: "+record.topic()+" - Valor:"+record.value());
        System.out.println("Chave: "+record.key());
        System.out.println("Valor: "+record.value());
        System.out.println("Partição: "+record.partition());
        System.out.println("Offset: "+record.offset());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Email sent!");
        System.out.println("#########################################");

    }
}
