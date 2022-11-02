package pt.isec.pa.apoio_poe.utils;

public abstract class Texts {
    public void logo() {
        System.out.println("#######     ######      #######         ######");
        System.out.println("   #        #           #               #     ");
        System.out.println("   #        #####       ####            #     ");
        System.out.println("   #            #       #               #     ");
        System.out.println("#######     #####       #######         ######");


    }
    public String menu_begin() {
        return "1- Start\n 2- Quit";
    }

    public static String configuration_menu() {
        return """
                1- Importar ficheiro CSV
                2- Gerir alunos
                3- Gerir docentes
                4- Geir propostas
                5- Fechar fase
                6- Avançar próxima fase
                """;
    }


}
