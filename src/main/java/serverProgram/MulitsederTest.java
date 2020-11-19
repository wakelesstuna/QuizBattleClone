package serverProgram;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MulitsederTest {

    List<ObjectOutputStream> objoutlsit = new ArrayList<>();

    public void addOutStream(ObjectOutputStream objout){
        objoutlsit.add(objout);
    }

    public List<ObjectOutputStream> getObjoutlsit() {
        return objoutlsit;
    }
}
