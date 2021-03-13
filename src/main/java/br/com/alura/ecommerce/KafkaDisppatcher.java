package br.com.alura.ecommerce;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.Closeable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaDisppatcher implements Closeable {

    private final KafkaProducer<String, String> producer;

    KafkaDisppatcher () {
        this.producer = new KafkaProducer<String,String>( properties() );


    }

    private static Properties properties() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;

    }


    void send(String topic, String key, String value) throws ExecutionException, InterruptedException {

        Callback callback = (data, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }
            System.out.println("Sucesso enviando " + data.topic() + ":::partition " + data.partition() + " /offset " + data.offset() + " /timestamp " + data.timestamp());
        };

        var record = new ProducerRecord<String, String>(topic, key, value);
        producer.send( record, callback).get();
    }

    @Override
    public void close() {
        producer.close();
    }
}
