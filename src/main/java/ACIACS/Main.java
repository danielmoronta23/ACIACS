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
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<img  HEIGHT=\"290\" src=\"data:image/png;base64,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\"\n" +
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
