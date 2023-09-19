package com.qf.common.sdk.utils;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.X509Certificate;

/**
 * CA签名工具类
 * @author 千锋健哥
 */
@Slf4j
public class X509Util {

    public static SSLSocketFactory getSSLSocketFactory(final String caCrtFile, final String crtFile, final String keyFile, final String password) {
        try {
            Security.addProvider(new BouncyCastleProvider());

            // load CA certificate
            X509Certificate caCert = loadCertificate(caCrtFile);
            // CA certificate is used to authenticate server
            KeyStore caKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            caKeyStore.load(null, null);
            caKeyStore.setCertificateEntry("cacertfile", caCert);
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(caKeyStore);

            // load client certificate
            X509Certificate cert = loadCertificate(crtFile);
            // load client private key
            KeyPair key = loadCertificateWithPassword(keyFile,password);
            // client key and certificates are sent to server so it can authenticate us
            KeyStore certKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            certKeyStore.load(null, null);
            certKeyStore.setCertificateEntry("certfile", cert);
            certKeyStore.setKeyEntry("keyfile", key.getPrivate(), password.toCharArray(), new java.security.cert.Certificate[]{cert});
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(certKeyStore, password.toCharArray());

            // finally, create SSL socket factory
            SSLContext context = SSLContext.getInstance("TLSv1.2");
            context.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

            return context.getSocketFactory();
        } catch (Exception e) {
            //throw new RuntimeException(e.getMessage());
            //System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private static <T> T loadCertificate(String caCrtFile) throws IOException {
        return loadCertificateWithPassword(caCrtFile, null);
    }

    @SuppressWarnings("unchecked")
    private static <T> T loadCertificateWithPassword(String caCrtFile, String password) throws IOException {
        PEMReader reader = null;
        try {
            String classPath = "classpath:";
            if (caCrtFile.startsWith(classPath)) {
                reader = null != password ? new PEMReader(new InputStreamReader(X509Util.class.getResourceAsStream(caCrtFile.replace(classPath, ""))), password::toCharArray)
                        : new PEMReader(new InputStreamReader(X509Util.class.getResourceAsStream(caCrtFile.replace(classPath, ""))));
            } else {
                reader = null != password ? new PEMReader(new InputStreamReader(new ByteArrayInputStream(Files.readAllBytes(Paths.get(caCrtFile)))), password::toCharArray)
                        : new PEMReader(new InputStreamReader(new ByteArrayInputStream(Files.readAllBytes(Paths.get(caCrtFile)))));
            }
            return (T) reader.readObject();
        } finally {
            if (null != reader) reader.close();
        }
    }


    public static void main(String[] args) {
        InputStream resourceAsStream = X509Util.class.getResourceAsStream("/certs/ca-cert.pem");
        System.out.println(resourceAsStream);
    }
}
