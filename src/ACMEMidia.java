import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ACMEMidia {
    private final String nomeArquivoEntrada = "entrada.txt";
    private final String nomeArquivoSaida = "saida.txt";

    public void executar() {

        Midiateca midiateca = new Midiateca();

        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivoEntrada))) {
            //saída por arquivo
            PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));

            String linhaOut1="";
            String linhaOut2="";
            String linhaOut3="";
            String linhaOut4="";
            String linhaOut5="";
            String linhaOut6="";
            String linhaOut7="";
            String linhaOut8="";

                Object[] linhas = leitor.lines().toArray();

                int codV = parseInt(linhas[0].toString());
                String tituloV = linhas[1].toString();
                int anoV = parseInt(linhas[2].toString());
                Categoria categoriaV = Categoria.valueOf(linhas[3].toString());
                int qualidadeV = parseInt(linhas[4].toString());

                //Cadastrar vídeos: lê todos os dados de cada vídeo e, se o código não for repetido
                //no sistema, cadastra-o no sistema.
                // Se o código da vídeo for repetido mostra a
                //mensagem no formato: 1:Erro-video com codigo repetido: codigo
                //Para cada vídeo cadastrado com sucesso no sistema, mostra os dados da vídeo
                //no formato: 1:codigo,titulo,ano,categoria,qualidade
                Video video = new Video(codV, tituloV, anoV, categoriaV, qualidadeV);
                boolean cadastro1 = midiateca.cadastraMidia(video);
                List<Midia> midias1 = midiateca.getMidias();
                if(!cadastro1){
                    linhaOut1 = "1:Erro-video com codigo repetido "+codV+"\n";
                }else{
                    linhaOut1 = "1:"+codV+","+tituloV+","+anoV+","+categoriaV.getNome()+","+qualidadeV+"\n";
                }
            streamSaida.write(linhaOut1.getBytes(StandardCharsets.UTF_8));

        if(linhas[5].toString().equals("-1")) {
            int codM = parseInt(linhas[6].toString());
            String tituloM = linhas[7].toString();
            int anoM = parseInt(linhas[8].toString());
            Categoria categoriaM = Categoria.valueOf(linhas[9].toString());
            double duracaoM = Double.parseDouble(linhas[10].toString());

            //Cadastrar músicas: lê todos os dados de cada música e, se o código não for
            //repetido no sistema, cadastra-a no sistema. Se o código da música for repetido
            //mostra a mensagem no formato: 2:Erro-musica com codigo repetido:
            //codigo.
            //Para cada música cadastrada com sucesso no sistema, mostra os dados da música
            //no formato: 2:codigo,titulo,ano,categoria,duração
                Musica musica = new Musica(codM, tituloM, anoM, duracaoM, categoriaM);
                boolean cadastro2 = midiateca.cadastraMidia(musica);
            if(!cadastro2){
                linhaOut2 = "2:Erro-musica com codigo repetido "+codM+"\n";
            }else {
                linhaOut2 = "2:" + codM + "," + tituloM + "," + anoM + "," + categoriaM.getNome() + "," + duracaoM + "\n";
            }
            streamSaida.write(linhaOut2.getBytes(StandardCharsets.UTF_8));

            if(linhas[11].toString().equals("-1")) {

                //Mostrar os dados de uma determinada mídia: lê o código de uma mídia. Se não
                //existir uma mídia com o código indicado, mostra a mensagem de erro: 3:Codigo
                //inexistente.
                //Se existir, mostra os dados da mídia no formato:
                //3:atributo1,atributo2,atributo3,...,valor da locação
            Midia midiaCod = midiateca.consultaPorCodigo(parseInt(linhas[12].toString()));
            if(midiaCod==null) {
                linhaOut3 = "3:Codigo inexistente."+"\n";
            }else{
                if(midiaCod instanceof Musica){
                    Musica m = (Musica) midiaCod;
                    linhaOut3 = "3:"+m.codigo+","+m.titulo+","+m.ano+","+m.getCategoria()+","+m.getDuracao()+","+m.calculaLocacao()+"\n";
                }else{
                    Video v = (Video) midiaCod;
                    linhaOut3 = "3:"+v.codigo+","+v.titulo+","+v.ano+","+v.getCategoria()+","+v.getQualidade()+","+v.calculaLocacao()+"\n";
                }
            }
                streamSaida.write(linhaOut3.getBytes(StandardCharsets.UTF_8));

                //Mostrar os dados de mídia(s) de uma determinada categoria: lê a categoria de
                //uma mídia. Se não existir uma mídia com a categoria indicada, mostra a mensagem
                //de erro: 4:Nenhuma midia encontrada.
                //Se existir, mostra os dados da(s) mídia(s) no formato:
                //4:atributo1,atributo2,atributo3,...,valor da locação
            ArrayList<Midia> midiaCat = midiateca.consultaPorCategoria(Categoria.valueOf(linhas[13].toString()));
            if(midiaCat==null) {
                linhaOut4 = "4:Nenhuma midia encontrada."+"\n";
            }else{
                for(Midia midia: midiaCat){
                    if(midia instanceof Musica){
                        Musica m = (Musica) midia;
                        linhaOut4 = "4:"+m.codigo+","+m.titulo+","+m.ano+","+m.getCategoria()+","+m.getDuracao()+","+m.calculaLocacao()+"\n";
                    }else{
                        Video v = (Video) midia;
                        linhaOut4 = "4:"+v.codigo+","+v.titulo+","+v.ano+","+v.getCategoria()+","+v.getQualidade()+","+v.calculaLocacao()+"\n";
                    }
                }
            }
                streamSaida.write(linhaOut4.getBytes(StandardCharsets.UTF_8));

                //Mostrar os dados de vídeo(s) de uma determinada qualidade: lê a qualidade
                //de vídeo. Se não existir a qualidade indicada, mostra a mensagem de erro:
                //5:Qualidade inexistente.
                //Se existir, mostra os dados do(s) vídeos(s) no formato:
                //5:atributo1,atributo2,atributo3,...,valor da locação
                ArrayList<Video> videoQua = midiateca.consultaPorQualidade(parseInt(linhas[14].toString()));
                if(videoQua==null) {
                    linhaOut5 = "5:Qualidade inexistente."+"\n";
                }else{
                    for(Video v: videoQua){
                            linhaOut5 = "5:"+v.codigo+","+v.titulo+","+v.ano+","+v.getCategoria()+","+v.getQualidade()+","+v.calculaLocacao()+"\n";
                        }
                    }
                streamSaida.write(linhaOut5.getBytes(StandardCharsets.UTF_8));
                }

            //Mostrar os dados da música de maior duração: localiza a música cadastrada
            //com maior duração. Se não existir nenhuma música cadastrada, mostra a
            //mensagem de erro: 6:Nenhuma música encontrada.
            //Se existir, mostra os dados da música no formato: 6:titulo,duracao
            Musica musicaDur = midiateca.consultaPorDuracao();
            if(musicaDur==null) linhaOut6 = "6:Nenhuma música encontrada."+"\n";
            else linhaOut6 = "6:" + musicaDur.titulo + "," + musicaDur.getDuracao()+"\n";
            streamSaida.write(linhaOut6.getBytes(StandardCharsets.UTF_8));

            //Remover uma mídia: lê o código de uma mídia. Se não existir uma mídia com o
            //código indicado, mostra a mensagem de erro: 7:Codigo inexistente.
            //Se existir, mostra os dados da mídia no formato:
            //7:atributo1,atributo2,atributo3,...,valor da locação e depois a
            //remove do sistema
            Midia midiaConToRem = midiateca.consultaPorCodigo(parseInt(linhas[15].toString()));
            if(midiaConToRem==null) linhaOut7 = "7:Codigo inexistente."+"\n";
            else{
                if(midiaConToRem instanceof Musica) {
                    Musica m = (Musica) midiaConToRem;
                    linhaOut7 = "7:" + m.codigo + "," + m.titulo + "," + m.ano + "," + m.getDuracao()+","+m.getCategoria()+","+m.calculaLocacao()+"\n";
                    boolean midiaRem = midiateca.removeMidia(parseInt(linhas[15].toString()));
                }else if(midiaConToRem instanceof Video) {
                    Video v = (Video) midiaConToRem;
                    linhaOut7 = "7:"+v.codigo+","+v.titulo+","+v.ano+","+v.getCategoria()+","+v.getQualidade()+","+v.calculaLocacao()+"\n";
                    boolean midiaRem = midiateca.removeMidia(parseInt(linhas[15].toString()));
                }
            }
            streamSaida.write(linhaOut7.getBytes(StandardCharsets.UTF_8));

            //Mostrar o somatório de locações de todas as mídias: calcula o somatório do
            //valor de locação de todas as mídias do sistema. Se não existir mídia cadastrada
            //no sistema, mostra a mensagem de erro: 8:Nenhuma midia encontrada.
            //Se existir, mostra a mensagem no formato: 8:valor do somatório
            double soma = midiateca.somaLocacoes();
            if(soma == 0) {
                linhaOut8 = "8:Nenhuma midia encontrada."+"\n";
            }else{
                linhaOut8 = "8:"+soma+"\n";
            }
            streamSaida.write(linhaOut8.getBytes(StandardCharsets.UTF_8));
                }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        } finally {

        }
    }
}