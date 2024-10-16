package com.codean.remidiujian2.utility;

import com.codean.remidiujian2.models.dtos.CatatanParkirDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.xwpf.usermodel.*;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;



@Configuration
public class ConvertToFile {
    public void saveUserDtoListToTxt(List<CatatanParkirDTO> catatanParkirDTOList) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        File file = new File("/home/trian/coba_simpan_file/parkir.txt");

        for (CatatanParkirDTO dto : catatanParkirDTOList) {
            //mengonversi objek ke JSON dan menambahkannya ke StringBuilder
            sb.append(objectMapper.writeValueAsString(dto));
            //menambahkan separator di setiap data
            sb.append(System.lineSeparator());
            objectMapper.writer();
        }
        //menulis isi string builder ke file
        //Files.write(Paths.get(file.toURI()), sb.toString().getBytes(StandardCharsets.UTF_8));

        objectMapper.writeValue(file, sb);

    }

    public void saveUserDtoListToPdf(List<CatatanParkirDTO> catatanParkirDTOS) throws IOException, DocumentException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("/home/trian/coba_simpan_file/parkir.pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);

        for (CatatanParkirDTO catatanParkirDTO : catatanParkirDTOS) {
            String userJson = convertToJson(catatanParkirDTO);
            Paragraph paragraph = new Paragraph(userJson, font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setSpacingAfter(100);
            document.add(paragraph);
        }

        document.close();

    }

    public void saveUserDtoListToDocx(List<CatatanParkirDTO> catatanParkirDTOS) throws Docx4JException {
        WordprocessingMLPackage wordPakage = WordprocessingMLPackage.createPackage();
        MainDocumentPart mainDocumentPart = wordPakage.getMainDocumentPart();
        mainDocumentPart.addStyledParagraphOfText("Title", "TEST CREATE JSON TO WORD");

        for (CatatanParkirDTO dto : catatanParkirDTOS) {
            String userJson = convertToJson(dto);
            mainDocumentPart.addParagraphOfText(userJson);
        }

        File exportFile = new File("/home/trian/coba_simpan_file/parkir.docx");
        wordPakage.save(exportFile);

    }

    public void saveUserDtoListToTxt(CatatanParkirDTO catatanParkirDTO) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        File file = new File("/home/trian/coba_simpan_file/parkir.txt");


        //mengonversi objek ke JSON dan menambahkannya ke StringBuilder
        sb.append(objectMapper.writeValueAsString(catatanParkirDTO));
        //menambahkan separator di setiap data
        sb.append(System.lineSeparator());

        //menulis isi string builder ke file
        Files.write(Paths.get(file.toURI()), sb.toString().getBytes(StandardCharsets.UTF_8));
    }

    public void saveUserDtoListToPdf(CatatanParkirDTO catatanParkirDTO) throws IOException, DocumentException {

        String pdfFilePath = "/home/trian/coba_simpan_file/parkir.pdf";

        OutputStream fos = new FileOutputStream(new File(pdfFilePath));

        float width = 10 * 28.35f;
        float length =  10 * 28.35f;
        Document document = new Document(new com.itextpdf.text.Rectangle(0, 0, width, length));
        PdfWriter.getInstance(document, fos);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        document.open();
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(15);
        Font judul = new Font(Font.FontFamily.TIMES_ROMAN,20.0f,Font.UNDERLINE,BaseColor.BLACK);

        paragraph.add(new Chunk("STRUK PARKIR", judul));
        paragraph.add(new Chunk("\n"));
        paragraph.add(new Chunk("\n"));
        paragraph.add(new Chunk("\n"));
        paragraph.add(new Chunk("ID : " + catatanParkirDTO.getId()));
        paragraph.add(new Chunk("\n"));
        paragraph.add(new Chunk("ID Kendaraan : " + catatanParkirDTO.getIdKendaraan()));
        paragraph.add(new Chunk("\n"));
        paragraph.add(new Chunk("ID Slot Parkir : " + catatanParkirDTO.getIdSlotParkir()));
        paragraph.add(new Chunk("\n"));
        paragraph.add(new Chunk("Waktu Masuk : " + formatter.format(catatanParkirDTO.getWaktuMasuk())));
        paragraph.add(new Chunk("\n"));
        paragraph.add(new Chunk("Waktu Keluar : " + formatter.format(catatanParkirDTO.getWaktuKeluar())));
        paragraph.add(new Chunk("\n"));
        paragraph.add(new Chunk("Biaya Parkir : Rp." + catatanParkirDTO.getBiayaParkir()));

        document.add(paragraph);
        document.close();
        fos.close();

    }

    public void saveUserDtoListToDocx(CatatanParkirDTO catatanParkirDTO) throws IOException {

        XWPFDocument document = new XWPFDocument();

        CTDocument1 document1 = document.getDocument();
        CTBody body = document1.getBody();

        CTSectPr sectPr = body.addNewSectPr();

        // Menambahkan Page Size
        CTPageSz pageSz = sectPr.addNewPgSz();
        pageSz.setW(BigInteger.valueOf(6000));
        pageSz.setH(BigInteger.valueOf(3800));

        CTPageMar pageMar = sectPr.addNewPgMar();
        pageMar.setTop(BigInteger.valueOf(300));
        pageMar.setBottom(BigInteger.valueOf(300));
        pageMar.setLeft(BigInteger.valueOf(300));
        pageMar.setRight(BigInteger.valueOf(300));

        // Menambahkan judul
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("STRUK PARKIR");
        titleRun.setBold(true);
        titleRun.setFontSize(15);
        titleRun.setUnderline(UnderlinePatterns.DOUBLE);
        titleRun.addBreak();

        // Format tanggal
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        XWPFParagraph subTitle = document.createParagraph();
        subTitle.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun subTitleRun = subTitle.createRun();
        subTitleRun.setText("ID : " + catatanParkirDTO.getId());
        subTitleRun.addBreak();
        subTitleRun.setText("ID Kendaraan : " + catatanParkirDTO.getIdKendaraan());
        subTitleRun.addBreak();
        subTitleRun.setText("ID Slot Parkir : " + catatanParkirDTO.getIdSlotParkir());
        subTitleRun.addBreak();
        subTitleRun.setText("Waktu Masuk : " + formatter.format(catatanParkirDTO.getWaktuMasuk()));
        subTitleRun.addBreak();
        subTitleRun.setText("Waktu Keluar : " + formatter.format(catatanParkirDTO.getWaktuKeluar()));
        subTitleRun.addBreak();
        subTitleRun.setText("Biaya Parkir : Rp." + catatanParkirDTO.getBiayaParkir());

        try (FileOutputStream out = new FileOutputStream("/home/trian/coba_simpan_file/parkir.docx")) {
            document.write(out);
        } finally {
            document.close();
        }
    }

    //    public void saveUserDtoListToDocx(CatatanParkirDTO catatanParkirDTO) throws Docx4JException {
//        WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
//        MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
//
//        mainDocumentPart.addStyledParagraphOfText("Title", "STRUCK PARKIR");
//
//
//        try {
//            String userJson = convertToJson(catatanParkirDTO);
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode jsonNode = objectMapper.readTree(userJson);
//
//            // iterasi untuk setiap filed di JSON
//            jsonNode.fieldNames().forEachRemaining(field -> {
//                String value = jsonNode.get(field).toString();
//                mainDocumentPart.addParagraphOfText(field + ": " + value);
//            });
//
//        } catch (Exception e) {
//            throw new RuntimeException("gagal membuat dokumen!");
//        }
//
//        File exportFile = new File("D:\\coba penyimpanan file\\parkir.docx");
//        wordPackage.save(exportFile);
//    }

    private String convertToJson(CatatanParkirDTO parkir) {
        return new Gson().toJson(parkir);
    }

}
