package utilities;

import java.io.*;
import java.util.HashMap;

public class Serialization {
    public static byte[] SerializeObject(Object object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        oos.writeObject(object);
        return baos.toByteArray();
    }

    public static <T> T DeserializeObject(byte[] buff) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(buff);
        ObjectInputStream ois = new ObjectInputStream(bais);

        return (T) ois.readObject();
    }

    public static HashMap<String, byte[]> serializeWholeMap(HashMap map) throws IOException {
        HashMap<String,byte[]> result = new HashMap<>();
        for(Object k:map.keySet()){
            result.put((String) k,Serialization.SerializeObject(map.get(k)));
        }
        return result;
    }
}