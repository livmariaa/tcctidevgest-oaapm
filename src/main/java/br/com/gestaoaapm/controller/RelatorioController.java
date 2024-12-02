package br.com.gestaoaapm.controller;

import br.com.gestaoaapm.model.Receita;
import br.com.gestaoaapm.repository.ReceitaRepository;
/*import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class RelatorioController {

    @Autowired
    private ReceitaRepository receitaRepository;

    @GetMapping("/relatorio")
    public String gerarRelatorio(
            @RequestParam(value = "dataInicial", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicial,
            @RequestParam(value = "dataFinal", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFinal,
            Model model) {


        List<Receita> receitas;
        double total = 0;

        if (dataInicial == null || dataFinal == null) {
            receitas = receitaRepository.findAll();
        } else {
            receitas = receitaRepository.findByDataPagamentoBetween(dataInicial, dataFinal);
        }

        for(Receita r : receitas){
            total += r.getValorReceita();
        }

        model.addAttribute("receitas", receitas);
        model.addAttribute("total", total);
        model.addAttribute("dataInicial", dataInicial);
        model.addAttribute("dataFinal", dataFinal);
        return "home/relatorio"; //Nome da sua página HTML do relatório
    }






    /*@GetMapping("/relatorio/pdf")
    public void gerarRelatorioPDF(
            @RequestParam(value = "dataInicial", required = false) String dataInicialParam,
            @RequestParam(value = "dataFinal", required = false) String dataFinalParam,
            HttpServletResponse response) throws IOException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dataInicial = null, dataFinal = null;

        try {
            if (dataInicialParam != null && !dataInicialParam.isEmpty()) {
                dataInicial = formatter.parse(dataInicialParam);
            }
            if (dataFinalParam != null && !dataFinalParam.isEmpty()) {
                dataFinal = formatter.parse(dataFinalParam);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            // Lidar com o erro (exibir mensagem de erro para o usuário)
            return;
        }

        List<Receita> receitas;
        double total = 0;

        if (dataInicial == null || dataFinal == null) {
            receitas = receitaRepository.findAll();
        } else {
            receitas = receitaRepository.findByDataPagamentoBetween(dataInicial, dataFinal);
        }

        for (Receita r : receitas) {
            total += r.getValorReceita();
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"relatorio_receitas.pdf\"");

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                contentStream.newLineAtOffset(50, 750);
                contentStream.showText("Relatório de Receitas");
                contentStream.endText();
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()); //Formato para exibir na tela

                if (dataInicial != null && dataFinal != null) {
                    contentStream.newLineAtOffset(50, 720);
                    contentStream.showText("Período: " + df.format(dataInicial) + " - " + df.format(dataFinal));
                }
                contentStream.newLineAtOffset(50, 680);
                contentStream.showText("Data do Pagamento");
                contentStream.newLineAtOffset(150, 0);
                contentStream.showText("Comprador");
                contentStream.newLineAtOffset(150, 0);
                contentStream.showText("Forma de Pagamento");
                contentStream.newLineAtOffset(150, 0);
                contentStream.showText("Valor");
                contentStream.newLineAtOffset(-450, -20);

                int y = 650;
                for (Receita receita : receitas) {
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText(df.format(receita.getDataPagamento()));
                    contentStream.newLineAtOffset(150, 0);
                    contentStream.showText(receita.getPessoa().getNome());
                    contentStream.newLineAtOffset(150, 0);
                    contentStream.showText(receita.getFormaPagamento().getDescricao());
                    contentStream.newLineAtOffset(150, 0);
                    contentStream.showText("R$ " + String.format("%.2f", receita.getValorReceita()));
                    contentStream.newLineAtOffset(-450, 0);
                    y -= 20;
                }

                contentStream.newLineAtOffset(0, -40);
                contentStream.showText("Total: R$ " + String.format("%.2f", total));
                contentStream.endText();
            }
            document.save(response.getOutputStream());
        }
    }*/
    }


