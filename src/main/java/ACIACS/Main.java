package ACIACS;

import ACIACS.API.ApiRest;
import ACIACS.controladores.ControladorPlantilla;
import ACIACS.controladores.ControladorSOAP;
import ACIACS.logica.Controladora;
import ACIACS.servicios.ConexionDB;
import ACIACS.util.EnviarMensajeUsandoJavamail;
import io.javalin.Javalin;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {

        System.out.println("Haciendo TEST de envio de mensaje: ");
        try {
            new EnviarMensajeUsandoJavamail().enviarMensaje("danielmoronta23@hotmail.com","TEST",mensajeHTML(),"TEST");
            System.out.println("Se ha enviado el mensaje al correo electronico.");
        } catch (IOException e) {
            System.out.println("NO SE PUDO ENVIAR EL MENSAJE...");
            e.printStackTrace();
        }

        System.out.println("Test completado\n");
        Javalin app = Javalin.create(config ->{
            config.addStaticFiles("/Visual"); //Desde la carpeta de resources.
            config.enableCorsForAllOrigins();
        });
        try {
            ConexionDB.getInstance();
            Controladora.getInstance().datosPruebas(); //.crearDatosPorDefecto();
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        new ControladorSOAP(app).aplicarRutas();
        new ApiRest(app).aplicarRutas();
        new ControladorPlantilla(app).aplicarRutas();

        app.start(7000);
    }

    
    //PRUEBA RAPIDA Xd
    public static String mensajeHTML() {
        String mensaje = " <!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "<meta name=\"viewport\" content=\"initial-scale=1.0\">\n" +
                "<meta name=\"format-detection\" content=\"telephone=no\">\n" +
                "<meta content=\"IE=edge,chrome=1\" http-equiv=\"X-UA-Compatible\">\n" +
                "<link href=\"http://fonts.googleapis.com/css?family=Oswald|Open+Sans&subset=latin,latin-ext\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300&subset=latin,latin-ext' rel='stylesheet' type='text/css'>\n" +
                "<style type=\"text/css\">\n" +
                "body {\n" +
                "\twidth:100% !important;\n" +
                "\t-webkit-text-size-adjust:100%;\n" +
                "\t-ms-text-size-adjust:100%;\n" +
                "\tmargin:0;\n" +
                "\tpadding:0;\n" +
                "\tline-height:100%;\n" +
                "}\n" +
                "#outlook a {\n" +
                "\tpadding:0;\n" +
                "}\n" +
                "\n" +
                ".ExternalClass,\n" +
                ".ExternalClass p,\n" +
                ".ExternalClass span,\n" +
                ".ExternalClass font,\n" +
                ".ExternalClass td,\n" +
                ".ExternalClass div {\n" +
                "\tline-height:100%;\n" +
                "    border-collapse: collapse;\n" +
                "    \n" +
                "}\n" +
                ".tablereset {\n" +
                "\tmargin:0 auto;\n" +
                "\twidth:680px !important;\n" +
                "\tline-height:100% !important\n" +
                "}\n" +
                "img {\n" +
                "\toutline:none;\n" +
                "\ttext-decoration:none;\n" +
                "\tborder:none;\n" +
                "\t-ms-interpolation-mode:bicubic;\n" +
                "    max-width: 100%!important;\n" +
                "    margin: 0;\n" +
                "    padding: 0;\n" +
                "    display: block;\n" +
                "}\n" +
                "table td {\n" +
                "\tborder-collapse:collapse;\n" +
                "}\n" +
                "table {\n" +
                "\tborder-collapse:collapse; \n" +
                "    mso-table-lspace:0pt; \n" +
                "    mso-table-rspace:0pt;\n" +
                "}\n" +
                "   \n" +
                "    td[class*=font-resize] {font-size: 140%!important}\n" +
                "    td[style*=\"Oswald\"] {font-family: 'Oswald', Arial, Helvetica, sans-serif!important}\n" +
                "    td[style*=\"Open Sans\"] {font-family: 'Open Sans', arial, sans-serif !important}    \n" +
                "\n" +
                "@media only screen and (max-width:680px) {\n" +
                "\ta[href=\"tel\"],\n" +
                "\ta[href=\"sms\"] {\n" +
                "\t\ttext-decoration:none;\n" +
                "\t\tcolor:#ffffff;\n" +
                "\t\tpointer-events:none;\n" +
                "\t\tcursor:default;\n" +
                "\t}\n" +
                "\ttr.removemobile {\n" +
                "\t\tdisplay:none;\n" +
                "\t}\n" +
                "\ttable.removemobile {\n" +
                "\t\tdisplay:none;\n" +
                "\t}\n" +
                "\ttable.tablereset {\n" +
                "\t\tmargin:0 auto;\n" +
                "\t\twidth:440px !important;\n" +
                "\t\tline-height:100% !important\n" +
                "\t}\n" +
                "\ttable[class*=devicewidth] {\n" +
                "\t\twidth:440px!important;\n" +
                "\t\ttext-align:center!important;\n" +
                "        float: none!important;\n" +
                "        display: table!important;\n" +
                "\t}\n" +
                "\ttable[class*=devicewidth] .banner img {\n" +
                "\t\twidth: 440px!important;\n" +
                "\t}\n" +
                "\ttable[class*=devicewidthinner] {\n" +
                "\t\twidth:400px!important;\n" +
                "\t\ttext-align:center!important;\n" +
                "        float: none!important;\n" +
                "\t}\n" +
                "\ttable[class*=devicewidthinner] .banner img {\n" +
                "\t\twidth: 400px!important;\n" +
                "\t}\n" +
                "     table[class*=devicewidthinner3] {\n" +
                "\t\twidth:173px!important;\n" +
                "        float: none!important;\n" +
                "\t}\n" +
                "\timg[class*=logoImg] {\n" +
                "\t\twidth:110px!important;\n" +
                "\t\theight:auto!important;\n" +
                "\t}\n" +
                "}\n" +
                "@media only screen and (max-width:519px) {\n" +
                "    table.removemobile {\n" +
                "\t\tdisplay:none;\n" +
                "\t}\n" +
                "\ttable[class*=tablereset] {\n" +
                "\t\tmargin:0 auto;\n" +
                "\t\twidth:280px !important;\n" +
                "\t\tline-height:100% !important\n" +
                "\t}\n" +
                "    img[class*=logoImg] {\n" +
                "\t\twidth:100px!important;\n" +
                "\t\theight:auto!important;\n" +
                "\t}\n" +
                "    td[class*=threecolumnphoto] {\n" +
                "\t\tpadding-bottom: 20px;\n" +
                "\t}\n" +
                "\ttable[class*=devicewidthright] {\n" +
                "\t\twidth:160px!important;\n" +
                "\t}\n" +
                "\ttable[class*=devicewidth] {\n" +
                "\t\twidth:280px!important;\n" +
                "        float: none!important;\n" +
                "        display: table!important;        \n" +
                "\t}\n" +
                "\ttable[class*=devicewidth] .banner img {\n" +
                "\t\twidth: 280px!important;\n" +
                "\t}\n" +
                "\ttable[class*=devicewidthinner] {\n" +
                "\t\twidth:240px!important;\n" +
                "        float: none!important;\n" +
                "\t}\n" +
                "\ttable[class*=devicewidthinner] .banner img {\n" +
                "\t\twidth: 240px!important;\n" +
                "\t}\n" +
                "    table[class*=devicewidthinner3] {\n" +
                "\t\twidth:173px!important;\n" +
                "        float: none!important;\n" +
                "\t}\n" +
                "    table[class*=button] {\n" +
                "\t\tfloat: none!important;\n" +
                "\t}\n" +
                "\ttable[class*=button] td,\n" +
                "\ttable[class*=button] td a {\n" +
                "\t\tfont-size:12px!important;\n" +
                "\t}\n" +
                "    td[class*=oswaldfont] {\n" +
                "\t\tfont-size: large!important;\n" +
                "\t}\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body style=\"background-color:#ededed; margin:0; padding:0\">\n" +
                "<div style=\"background-color: #e7e6e6 \">\n" +
                "\t<br>\n" +
                "\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"table-layout:fixed\" width=\"100%\">\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td>\n" +
                "\n" +
                "\t\t\t\t<!-- ciemny banner -->\n" +
                "\n" +
                "\t\t\t\t<table class=\"tablereset\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"-moz-border-top-left-radius: 5px; -webkit-border-radius: 5px;\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=\"680\">\n" +
                "\t\t\t\t\t\t\t<table class=\"devicewidth\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"680\" bgcolor=\"#393939\">\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td width=\"680\" align=\"center\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<table class=\"devicewidthinner\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"580\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"580\" align=\"center\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<table class=\"devicewidthinner\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"290\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"520\" align=\"center\" style=\"font-size:1px;line-height:1px;\" height=\"26\" >&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"520\" align=\"center\" style=\"font-family:Arial,Helvetica,sans-serif, 'Open Sans';font-size:28px;line-height:1.3em;color:#ffffff;font-weight:300;text-align:left;\">ACIACS</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"520\" height=\"15\" align=\"center\" style=\"font-size:1px;line-height:1px;\">&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"520\" align=\"center\" style=\"font-family:Arial,Helvetica,sans-serif, 'Open Sans';font-size:17px;line-height:1.6em;color:#8b8b8b;font-weight:300;text-align:left;\">Mediante este C&oacute;digo QR podr&aacute;s aceder a nuestro local a traves del paso de prioridad.<br/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<br/>Solo debes presentarlo frente al esc&aacute;ner del sistema y posteriormente se le notificar&aacute; para que siga con el proceso de evaluaci&oacute;n.\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tcontinuar para evaluar si poses mascarilla y una optima temperatura corporal.</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"520\" height=\"30\" align=\"center\" style=\"font-size:1px;line-height:1px;\">&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<table class=\"devicewidthinner\" align=\"right\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"290\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"290\" align=\"center\" style=\"font-size:1px;line-height:1px;\" height=\"26\" width=\"290\">&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"240\" align=\"center\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<img  HEIGHT=\"290\" src=\"\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t alt=\"QR CODE\" border=\"0\" style=\"color:#000000; border:none;  display:block; font-size:22px;margin:0 auto;text-align:center;\" width=\"290\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<br>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<br>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\n" +
                "\n" +
                "\t\t\t\t<!-- koniec -->\n" +
                "\n" +
                "\n" +
                "                <table class=\"tablereset\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=\"680\">\n" +
                "\t\t\t\t\t\t\t<table  class=\"devicewidth\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"680\">\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td align=\"center\" style=\"font-size:1px; line-height:1px\" height=\"32\" width=\"680\">&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t\t<table class=\"tablereset\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=\"680\">\n" +
                "\t\t\t\t\t\t\t<table class=\"devicewidth\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"680\">\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td align=\"center\" style=\"font-size:1px; line-height:1px\" height=\"24\" width=\"680\">&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t\t<table class=\"tablereset\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=\"680\">\n" +
                "\t\t\t\t\t\t\t<table class=\"devicewidth\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"680\">\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td width=\"680\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<table class=\"devicewidthinner\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"580\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" width=\"580\" style=\"color:#8f8f8f; font-family:Arial, Helvetica, sans-serif; font-size:12px; line-height:1.4em\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\tMessage sent by ACIACS: RD Navarrete, Santiago de los Caballeros.\n" +
                "Phone Number. +44 12 3456 7890, email: support@aciacs.com, aciacs.azrael.studio.\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t\t<table class=\"tablereset\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=\"680\">\n" +
                "\t\t\t\t\t\t\t<table class=\"devicewidth\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"680\">\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td align=\"center\" style=\"font-size:1px; line-height:1px\" height=\"32\" width=\"680\">&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t\t<table class=\"tablereset\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=\"680\">\n" +
                "\t\t\t\t\t\t\t<table align=\"center\" class=\"devicewidth\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"134\">\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"middle\" style=\"font-family:Arial, Helvetica, sans-serif; padding:10px 0 10px 0px\" width=\"180\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.pucmm.edu.do\" style=\"color:#f1f1f1; font-size:10px; text-decoration:none\" target=\"_blank\" title=\"FreshMail.com\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<img class=\"logoImg\" src=\"https://www.pucmm.edu.do/recursos/PublishingImages/logosimbolo/Marca%20PUCMM%20%28Color%29.png\" alt=\"FreshMail\" border=\"0\" width=\"139\" style=\"color:#000000; border:none; font-size:22px; display:block; height:auto; max-height:139px;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t</a>\n" +
                "\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t\t<table class=\"tablereset\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td width=\"680\">\n" +
                "\t\t\t\t\t\t\t<table class=\"devicewidth\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"680\">\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td align=\"center\" style=\"font-size:1px; line-height:1px\" height=\"54\" width=\"680\">&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t</table>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html> ";


        return mensaje;
    }
}
