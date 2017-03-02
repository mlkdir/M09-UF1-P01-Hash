import java.io.File;
import java.io.IOException;

/**
 * Created by Dionis on 25/03/2016.
 */
public class hack {

    public static final String FITXER = "firmat.xml";

    public static void main(String[] args) throws IOException {

        File f = new File(FITXER);

        byte[] fb = Utils.read(f);
        byte[] foriginalbytes = new byte[fb.length];

        System.arraycopy(fb,0,foriginalbytes,0,fb.length);
        foriginalbytes[15] = '2';
        Utils.write(FITXER,foriginalbytes);

    }
}
