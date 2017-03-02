
import java.io.*;
import java.security.*;


/**
 * Created by Dionis on 25/03/2016.
 */
public class P01_part2 {

    public static final String PUBLIC_KEY_FILE = "public.key";

    public static final String FITXER_FINAL = "planetsF.xml";
    public static final String FITXER_SIGNAT = "firmat.xml";

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException {


        KeyPair keyPair = null;
        PublicKey pubk = null;

        File f = new File(FITXER_SIGNAT);

        ObjectInputStream inputStream = null;
        inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
        pubk = (PublicKey) inputStream.readObject();

        byte[] fb = Utils.read(f);
        byte[] foriginalbytes = new byte[fb.length-128];
        byte[] signatura = new byte[128];

        System.arraycopy(fb,0,foriginalbytes,0,fb.length-128);
        Utils.write(FITXER_FINAL,foriginalbytes);

        System.arraycopy(fb,fb.length-128,signatura,0,128);
        System.out.println("signatura Len: "+signatura.length);

        byte[] md5orignal = new byte[0];

        try {
            md5orignal = Utils.verificar(signatura,pubk);
        } catch (InvalidKeyException e) {
            System.out.println("La clau no és vàlida -->> FRAU");
            md5orignal = "FRAU".getBytes();
        }

        byte[] digestionat = Utils.digestiona(new File(FITXER_FINAL),"MD5");

        if(new String(md5orignal).equals(new String(digestionat))){
            System.out.println("ORIGINAL i VERIFICAT");
            System.out.println("md5 orignal: "+new String(md5orignal));
            System.out.println("md5 recuperat: "+new String(digestionat));
        }else{
            System.out.println("No coincideix el hash -> Codi modificat: FRAU");
        }

    }

}
