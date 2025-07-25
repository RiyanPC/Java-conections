import org.jasypt.util.text.BasicTextEncryptor;

public class Encrypter {
    public static void main(String[] args) {
        String clave = "bianca123";

        String textoAEncriptar = "";

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(clave);
        String textoEncriptado = textEncryptor.encrypt(textoAEncriptar);

        System.out.println("ENC(" + textoEncriptado + ")");
    }
}
