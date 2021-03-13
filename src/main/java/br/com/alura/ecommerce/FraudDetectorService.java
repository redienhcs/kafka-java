package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FraudDetectorService {

    public static void main(String[] args) {

        var fraudDetectorService = new FraudDetectorService();

        var service = new KafkaService( FraudDetectorService.class.getSimpleName() , "ECOMMERCE_NEW_ORDER",
                fraudDetectorService::parse );
        service.run();


    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("-----------------------------------------");
        System.out.println("Processing new order, checking for fraud");
        System.out.println("Chave: "+record.key());
        System.out.println("Valor: "+record.value());
        System.out.println("Partição: "+record.partition());
        System.out.println("Offset: "+record.offset());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Order processes");
        System.out.println("#########################################");
    }
}
