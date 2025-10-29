import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class ErabiltzaileaDB implements AutoCloseable {
    
    private final MongoClient mongoClient;
    private final MongoCollection<Document> collection;

    // Eraikitzaileak konexioa hasieratzen du
    public ErabiltzaileaDB(String host, int port, String dbName, String collectionName) {
        this.mongoClient = new MongoClient(host, port);
        MongoDatabase database = mongoClient.getDatabase(dbName);
        this.collection = database.getCollection(collectionName);
    }

    // --- CRUD Metodoak ---

    // CREATE (Sortu)
    public void sortu(Erabiltzailea erabiltzailea) {
        // Java objektua BSON Dokumentu bihurtu eta txertatu
        this.collection.insertOne(erabiltzailea.dokumentuBihurtu());
    }

    // READ (Irakurri)
    public Erabiltzailea izenezBilatu(String izena) {
        Document doc = collection.find(Filters.eq("izena", izena)).first();
        if (doc != null) {
            // BSON Dokumentua Java objektu bihurtu
            return new Erabiltzailea(doc.getString("izena"),doc.getInteger("adina"),doc.getString("hiria"));
        }
        return null;
    }

    // UPDATE (Eguneratu)
    public void adinaEguneratu(String izena, int adinBerria) {
        collection.updateOne(
            Filters.eq("izena", izena),
            Updates.set("adina", adinBerria)
        );
    }

    // DELETE (Ezabatu)
    public void ezabatu(String izena) {
        collection.deleteOne(Filters.eq("izena", izena));
    }

    // Bezeroaren konexioa ixten du
    @Override
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}