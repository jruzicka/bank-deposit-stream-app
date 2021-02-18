package org.jruzicka.kafka.training.bankbalance;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BankTransactionProducerTest {
    @Test
    public void newRandomTransactionTest(){
        ProducerRecord<String, String> record = BankTransactionsProducer.newRandomTransaction( "john");
        String key = record.key();
        String value = record.value();

        assertEquals(key, "john");
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(value);
            assertEquals(node.get("name").asText(), "john");
            assertTrue("Amount should be GT 0 and LT 100", node.get("amount").asInt() < 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(value);
    }

}
