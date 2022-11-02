package pt.isec.pa.apoio_poe.model.data;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import pt.isec.pa.apoio_poe.model.data.Alunos.DadosAluno;
import pt.isec.pa.apoio_poe.model.data.Docentes.DadosDocente;
import pt.isec.pa.apoio_poe.model.data.Propostas.TipoPropostas;

import static pt.isec.pa.apoio_poe.model.data.ManagementTypes.EXPORT;
import static pt.isec.pa.apoio_poe.model.data.ManagementTypes.IMPORT;

public  class FileManagement {


    private String importFileStudents(AppData data, String filename) {

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");

                data.addStudent(Long.parseLong(values[0]), values[1], values[2], values[3],
                        values[4], Double.parseDouble(values[5]), values[6].toLowerCase().equals("true"));


            }

        } catch (Exception e) {
            return "ERRO na leitura do ficheiro" + e;
        }
        return "File importdado com sucesso!";
    }


    private String importFileDocentes(AppData data, String filename) {

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");

                data.addDocente(values[0], values[1]);

            }

        } catch (Exception e) {
            return "ERRO na leitura do ficheiro" + e;
        }
        return "File importdado com sucesso!";
    }

    private String importFilePropostas(AppData data , String filename) {
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                i++;
                String[] values = line.split(",");

                switch (values[0]) {
                    case "T1" -> {
                        data.addEstagio(values[1], values[2],values[3],values[4]);
                        setStudentNumber(data, line, values);
                    }

                    case "T2" -> {
                        data.addProjeto(values[1],values[2],values[4], values[3]);

                        setStudentNumber(data, line, values);
                    }

                    case "T3" -> {
                        data.addAutoProposta(values[1],values[2]);
                        setStudentNumber(data,line,values );
                    }
                }
            }

        }
        catch (Exception e) {
            return "ERRO na leitura do ficheiro" + e;
        }
        return "File importdado com sucesso!";
    }

    private String importFileCandidaturas(AppData data, String filename) {

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                data.addCandidatura(Long.parseLong(values[0]));


                for (int i = 1; i < values.length; i++)
                    data.addProposalId(Long.parseLong(values[0]), values[i]);

            }
        }
        catch (Exception e) {
            return "ERRO na leitura do ficheiro" + e;
        }
        return "File importdado com sucesso!";
    }

    /*  END IMPORT FUNCTIONS*/

    /* EXPORT FILES*/
    public String writeFileAlunos(String filename, AppData data) {
        int j = 0;
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {

            List<String> values = new ArrayList<>();

            for (int i = 0; i < data.getNumberOfStudents(); i++) {
                j= 1;
                for (DadosAluno d: DadosAluno.values()) {
                    values.add(j-1, data.dataToExportAluno(d, i));
                    j++;
                }


                export(pw, values);
            }


        }catch (Exception e) {
           return("ERRO na leitura do ficheiro" + e);
        }
        return "Exportação realizada com sucesso!";
    }


    public String writeFileDocentes(String filename, AppData data) {
        int j;
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {

            List<String> values = new ArrayList<>();

            for (int i = 0; i < data.getNumberOfDocentes(); i++) {
                j = 1;
                for (DadosDocente d: DadosDocente.values()) {
                    values.add(j-1, data.dataToExportDocente(d, i));
                    j++;
                }


                export(pw, values);
            }


        }catch (Exception e) {
           return("ERRO na leitura do ficheiro" + e);
        }
        return "Exportação realizada com sucesso!";
    }

    private void export(PrintWriter pw, List<String> values) {
        Iterator<String> it = values.iterator();

        if (it.hasNext()) {
            pw.print(it.next());
        }

        while (it.hasNext()) {
            pw.print("," + it.next());
        }

        pw.println();

        values.clear();
    }

    public String writeFilePropostas(String filename, AppData data) {

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {

            List<String> values = new ArrayList<>();

            for (int i = 0; i < data.getNumberOfPropostas(); i++) {


                if(data.dataToExportProposta(-2, i).equals("1")) {
                    for (int j = 1; j <= 4; j++) {
                        if (data.dataToExportProposta(j, i) != "")
                            values.add(j-1, data.dataToExportProposta(j, i));
                    }
                }
                else {
                    for (int j = 1; j <= 6; j++) {
                        if (data.dataToExportProposta(j, i) != "")
                            values.add(j-1, data.dataToExportProposta(j, i));
                    }
                }


                export(pw, values);
            }


        }catch (Exception e) {
           return("ERRO na leitura do ficheiro" + e);
        }
        return "Exportação realizada com sucesso!";
    }


    public String writeFileCandidaturas(String filename, AppData data) {

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {

            List<String> values = new ArrayList<>();

            for (int i = 0; i < data.getNumberOfCandidaturas(); i++) {
                for (int j = 1; j <= 2; j++) {
                    values.add(j-1, data.dataToExportCandidatura(j, i));
                }

                for (String value : values) {
                    pw.print(value);
                }



                pw.println();

                values.clear();
            }


        }catch (Exception e) {
            return("ERRO na leitura do ficheiro" + e);
        }
        return "Exportação realizada com sucesso!";
    }

    /* END EXPORT FILES*/


    private boolean setStudentNumber(AppData data, String line, String[] values) {
        if (values.length == 6 && values[5].equals("-1")) {
            data.getPropostas().setStudentNumber(values[1], 0);
        }
        else if (values.length == 6) {
            data.getPropostas().setStudentNumber(values[1], Long.parseLong(values[5]));
        }
        else {
            return true;
        }
        return false;
    }

    public String importFile(ManagementTypes tipo, DataTypes dataType, String filename, AppData data) {

        switch (dataType) {
            case ALUNO -> {
                switch (tipo) {
                    case IMPORT -> {
                       return importFileStudents(data, filename);
                    }
                    case EXPORT -> {
                        return writeFileAlunos(filename,data);
                    }
                }
            }
            case DOCENTES -> {
                switch (tipo) {
                    case IMPORT -> {
                        return importFileDocentes(data, filename);
                    }
                    case EXPORT -> {
                        return writeFileDocentes(filename, data);
                    }
                }
            }
            case PROPOSTAS -> {
                switch (tipo) {
                    case IMPORT -> {
                        return importFilePropostas(data, filename);
                    }
                    case EXPORT -> {
                        return writeFilePropostas(filename, data);
                    }
                }

            }
            case CANDIDATURAS -> {
                switch (tipo) {
                    case IMPORT -> {
                        return importFileCandidaturas(data,filename);
                    }
                    case EXPORT -> {
                        return writeFileCandidaturas(filename, data);
                    }
                }

            }

        }
        return "Algum erro aconteceu!\n";
    }


}