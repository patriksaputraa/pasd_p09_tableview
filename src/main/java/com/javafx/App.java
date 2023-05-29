package com.javafx;

import com.javafx.Utilities;

import java.io.IOException;
import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends Application {
    TableView<BahanKimia> tabel;
    FilteredList<BahanKimia> filteredData;
    FileTeks file = new FileTeks(
            "D:\\Kuliah\\Semester 2\\Algoritma dan Struktur Data\\P09_TableView_72220533\\P09_VSC_TableView\\src\\main\\resources\\com\\example\\daftarBahan.csv");
    TextField txtIdBahan = new TextField();
    TextField txtNama = new TextField();
    TextField txtVolume = new TextField();
    TextField txtBentuk = new TextField();
    TextField txtSifatZat = new TextField();
    TextField txtTanggalTerima = new TextField();
    VBox vb = new VBox(5);
    HBox hb1 = new HBox(6);
    HBox hb2 = new HBox(4);
    ComboBox<String> comboBentuk = new ComboBox<>();
    Button btnAdd = new Button("_Add");
    Button btnUpdate = new Button("_Update");
    Button btnDelete = new Button("_Delete");
    Button btnClose = new Button("_Close");
    Button btnFind = new Button("_Find");
    Button btnFilter = new Button("_Filter");
    Button btnNew = new Button("_New");
    Button btnUndo = new Button("_Undo");
    Button btnSave = new Button("_Save");
    Pane reg = new Pane();
    Boolean baru = false;

    @Override
    public void start(Stage stage) throws IOException {
        tabel = new TableView<>();
        envManipulationData();
        btnUndo.setDisable(true);
        filteredData = new FilteredList<>(tabel.getItems(), b -> true);
        tabel.setItems(getBahanKimia());
        tabel.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // kolom kode
        TableColumn<BahanKimia, String> kolIdBahanKimia = new TableColumn<BahanKimia, String>("ID");
        kolIdBahanKimia.setCellValueFactory(new PropertyValueFactory<>("id_bahan_kimia"));
        kolIdBahanKimia.setMaxWidth(1f * Integer.MAX_VALUE * 5);
        kolIdBahanKimia.setStyle("-fx-alignment:center");
        // kolom nama bahan
        TableColumn<BahanKimia, String> kolNamaBahan = new TableColumn<BahanKimia, String>("Nama Bahan");
        kolNamaBahan.setCellValueFactory(new PropertyValueFactory<BahanKimia, String>("nama"));
        kolNamaBahan.setMaxWidth(1f * Integer.MAX_VALUE * 35);
        // kolom Volume
        TableColumn<BahanKimia, String> kolVolume = new TableColumn<BahanKimia, String>("Volume");
        kolVolume.setCellValueFactory(new PropertyValueFactory<BahanKimia, String>("volume"));
        kolVolume.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        // kolom bentuk
        TableColumn<BahanKimia, String> kolBentuk = new TableColumn<BahanKimia, String>("Bentuk");
        kolBentuk.setCellValueFactory(new PropertyValueFactory<BahanKimia, String>("bentuk"));
        kolBentuk.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        kolBentuk.setStyle("-fx-alignment:center");
        // kolom tingkat bahaya
        TableColumn<BahanKimia, String> kolSifatZat = new TableColumn<BahanKimia, String>("Sifat Zat");
        kolSifatZat.setCellValueFactory(new PropertyValueFactory<BahanKimia, String>("sifat_zat"));
        kolSifatZat.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        // kolom tanggal terima
        TableColumn<BahanKimia, String> kolTanggalTerima = new TableColumn<BahanKimia, String>("Tanggal Terima");
        kolTanggalTerima.setCellValueFactory(new PropertyValueFactory<BahanKimia, String>("tanggal_terima"));
        kolTanggalTerima.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        kolTanggalTerima.setStyle("-fx-alignment:center");

        comboBentuk.getItems().addAll("Liquid", "Gas", "Solid");

        tabel.getColumns().addAll(kolIdBahanKimia, kolNamaBahan, kolVolume, kolBentuk, kolSifatZat, kolTanggalTerima);

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String id_bahan, nama, volume, bentuk, sifat_zat, tanggal_terima;
                id_bahan = txtIdBahan.getText();
                nama = txtNama.getText();
                volume = txtVolume.getText();
                bentuk = comboBentuk.getValue();
                sifat_zat = txtSifatZat.getText();
                tanggal_terima = txtTanggalTerima.getText();
                if (id_bahan.equals("") || volume.equals("") || bentuk.equals("") || sifat_zat.equals("")
                        || tanggal_terima.equals("")) {
                    Utilities.warningAlert("WARNING", "Data anda masih ada yang kosong!", "Lengkapi data lebih dulu");
                } else {
                    ObservableList<BahanKimia> daftarBahan = tabel.getItems();
                    BahanKimia bahanKimia = new BahanKimia(id_bahan, nama, volume, bentuk, sifat_zat, tanggal_terima);
                    int idx, cacah = daftarBahan.size();
                    for (idx = 0; idx < cacah; idx++) {
                        if (Integer.parseInt(daftarBahan.get(idx).getId_bahan_kimia()) > Integer.parseInt(id_bahan)) {
                            tabel.getItems().add(idx, bahanKimia);
                            break;
                        }
                        if (Integer.parseInt(daftarBahan.get(idx).getId_bahan_kimia()) < Integer.parseInt(id_bahan)) {
                            tabel.getItems().add(bahanKimia);
                            break;
                        }
                    }
                    btnAdd.setDisable(true);
                    btnDelete.setDisable(false);
                    btnFilter.setDisable(false);
                    btnFind.setDisable(false);
                    btnUpdate.setDisable(false);
                    clearField();
                }
            }
        });

        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ObservableList<BahanKimia> pilih = tabel.getSelectionModel().getSelectedItems();
                String id, nama, volume, bentuk, chooseDelete = "";
                id = pilih.get(0).getId_bahan_kimia();
                nama = pilih.get(0).getNama();
                volume = pilih.get(0).getVolume();
                bentuk = pilih.get(0).getBentuk();
                chooseDelete = id + "\n" + nama + "\n" + volume + "\n" + bentuk;
                if (Utilities.confirmationAlert("DELETING DATA", chooseDelete,
                        "Apakah anda yakin ingin menghapus data ini?")) {
                    try {
                        delete();
                        Utilities.informationAlert("INFORMATION DELETE", chooseDelete, "Data berhasil dihapus!");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    Utilities.informationAlert("INFORMATION DELETE", chooseDelete, "Data tidak dihapus!");
                }
            }
        });

        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                envInputData();
                btnUndo.setDisable(false);
                baru = false;
                try {
                    update();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnClose.setOnAction(e -> stage.close());
        btnFilter.setOnAction(e -> dialogBoxFilter());
        btnFind.setOnAction(e -> cari());
        btnSave.setOnAction(e -> {
            try {
                save();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        btnNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                clearField();
                envInputData();
            }
        });
        btnUndo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                envInputData();
                String id_bahan, nama_bahan, volume, bentuk, sifat_zat, tgl_terima;
                ObservableList<BahanKimia> data = tabel.getItems();
                int lastIdx = data.size() - 1;
                BahanKimia lastItem = data.get(lastIdx);
                id_bahan = lastItem.getId_bahan_kimia();
                nama_bahan = lastItem.getNama();
                volume = lastItem.getVolume();
                bentuk = lastItem.getBentuk();
                sifat_zat = lastItem.getSifat_zat();
                tgl_terima = lastItem.getTanggal_terima();
                txtIdBahan.setText(id_bahan);
                txtNama.setText(nama_bahan);
                txtVolume.setText(volume);
                txtBentuk.setText(bentuk);
                txtSifatZat.setText(sifat_zat);
                txtTanggalTerima.setText(tgl_terima);
                txtNama.requestFocus();
                tabel.setItems(getBahanKimia());
            }
        });
        inisialisasiKontrol();
        stage.setScene(new Scene(vb, 750, 450));
        stage.setTitle("Inventarisasi Bahan Kimia");
        stage.show();
    }

    void envInputData() {
        txtIdBahan.setDisable(false);
        txtNama.setDisable(false);
        txtVolume.setDisable(false);
        txtBentuk.setDisable(false);
        txtSifatZat.setDisable(false);
        txtTanggalTerima.setDisable(false);
        comboBentuk.setDisable(false);
        btnAdd.setDisable(false);
        btnDelete.setDisable(true);
        btnFilter.setDisable(true);
        btnFind.setDisable(true);
        btnUpdate.setDisable(true);
        btnNew.setDisable(true);
        btnUndo.setDisable(false);
    }

    void envManipulationData(){
        txtIdBahan.setDisable(true);
        txtNama.setDisable(true);
        txtVolume.setDisable(true);
        txtBentuk.setDisable(true);
        txtSifatZat.setDisable(true);
        txtTanggalTerima.setDisable(true);
        comboBentuk.setDisable(true);
        btnAdd.setDisable(true);
        btnDelete.setDisable(false);
        btnFilter.setDisable(false);
        btnFind.setDisable(false);
        btnUpdate.setDisable(false);
        btnNew.setDisable(false);
    }

    public void clearField() {
        txtIdBahan.clear();
        txtNama.clear();
        txtVolume.clear();
        txtBentuk.clear();
        txtSifatZat.clear();
        txtTanggalTerima.clear();
    }

    public ObservableList<BahanKimia> getBahanKimia() {
        ObservableList<BahanKimia> brg = FXCollections.observableArrayList();
        try {
            String[] array_csv = this.file.read();
            String[] row;
            String id, nama, volume, bentuk, sifat, tgl_terima;
            if (!array_csv[0].equalsIgnoreCase("")) {
                for (int i = 0; i < array_csv.length; i++) {
                    row = array_csv[i].split(",");
                    id = row[0];
                    nama = row[1];
                    volume = row[2];
                    bentuk = row[3];
                    sifat = row[4];
                    tgl_terima = row[5];
                    brg.add(new BahanKimia(id, nama, volume, bentuk, sifat, tgl_terima));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return brg;
    }

    private String readAllElement() {
        ObservableList<BahanKimia> semua = tabel.getItems();
        String id, nama, volume, bentuk, sifat, tgl_terima, readString = "";
        for (BahanKimia bahan : semua) {
            id = bahan.getId_bahan_kimia();
            nama = bahan.getNama();
            volume = bahan.getVolume();
            bentuk = bahan.getBentuk();
            sifat = bahan.getSifat_zat();
            tgl_terima = bahan.getTanggal_terima();
            readString += id + "," + nama + "," + volume + "," + bentuk + "," + sifat + "," + tgl_terima + "\n";
        }
        return readString;
    }

    private void inisialisasiKontrol() {
        txtIdBahan.setMinWidth(40);
        txtIdBahan.setMaxWidth(60);
        txtIdBahan.setPromptText("ID");
        txtNama.setMinWidth(245);
        txtNama.setMaxWidth(550);
        txtNama.setPromptText("Nama Bahan Kimia");
        txtVolume.setMinWidth(30);
        txtVolume.setMaxWidth(130);
        txtVolume.setPromptText("Volume");
        comboBentuk.setMinWidth(80);
        comboBentuk.setMaxWidth(120);
        txtSifatZat.setMinWidth(150);
        txtSifatZat.setMaxWidth(255);
        txtSifatZat.setPromptText("Sifat Zat");
        txtTanggalTerima.setMinWidth(130);
        txtTanggalTerima.setMaxWidth(240);
        txtTanggalTerima.setPromptText("Tanggal Terima");
        comboBentuk.setValue("Liquid");
        btnAdd.setMinWidth(60);
        btnUpdate.setMinWidth(60);
        btnDelete.setMinWidth(60);
        btnFilter.setMinWidth(60);
        btnFind.setMinWidth(60);
        btnClose.setMinWidth(60);
        hb1.getChildren().addAll(txtIdBahan, txtNama, txtVolume, comboBentuk, txtSifatZat, txtTanggalTerima);
        HBox.setHgrow(reg, Priority.ALWAYS);
        HBox.setHgrow(txtIdBahan, Priority.ALWAYS);
        HBox.setHgrow(txtNama, Priority.ALWAYS);
        HBox.setHgrow(txtVolume, Priority.ALWAYS);
        HBox.setHgrow(comboBentuk, Priority.ALWAYS);
        HBox.setHgrow(txtSifatZat, Priority.ALWAYS);
        HBox.setHgrow(txtTanggalTerima, Priority.ALWAYS);
        hb2.getChildren().addAll(btnAdd, btnUpdate, btnDelete, btnFind, btnFilter, reg, btnNew, btnUndo, btnSave,
                btnClose);
        hb2.setPadding(new Insets(0, 5, 5, 5));
        hb1.setPadding(new Insets(0, 5, 0, 5));
        vb.getChildren().addAll(tabel, hb1, hb2);
    }

    private void update() throws IOException {
        String id_bahan, nama_bahan, volume, bentuk, sifat_zat, tgl_terima, updating;
        ObservableList<BahanKimia> pilih = tabel.getSelectionModel().getSelectedItems();
        id_bahan = pilih.get(0).getId_bahan_kimia();
        nama_bahan = pilih.get(0).getNama();
        volume = pilih.get(0).getVolume();
        bentuk = pilih.get(0).getBentuk();
        sifat_zat = pilih.get(0).getSifat_zat();
        tgl_terima = pilih.get(0).getTanggal_terima();
        updating = id_bahan + "\n" + nama_bahan + "\n" + volume + "\n" + bentuk + "\n" + sifat_zat + "\n" + tgl_terima;
        if (Utilities.confirmationAlert("UPDATING DATA", updating, "Apakah anda ingin mengupdate data ini?")) {
            txtIdBahan.setText(id_bahan);
            txtNama.setText(nama_bahan);
            txtVolume.setText(volume);
            txtBentuk.setText(bentuk);
            txtSifatZat.setText(sifat_zat);
            txtTanggalTerima.setText(tgl_terima);
            txtNama.requestFocus();
            delete();
        }
    }

    private void delete() throws IOException {
        ObservableList<BahanKimia> pilih, semua;
        semua = tabel.getItems();
        pilih = tabel.getSelectionModel().getSelectedItems();
        pilih.forEach(semua::remove);
        file.write(readAllElement());
    }

    String dialogBox() {
        Label lblId = new Label("ID Yang Dicari");
        TextField txtId = new TextField();
        txtId.setMaxWidth(70);
        txtId.setPromptText("ID Bahan Kimia");
        Button btnCari = new Button("_Find");
        HBox hb = new HBox(5, lblId, txtId, btnCari);
        hb.setPadding(new Insets(15, 10, 10, 10));
        Stage window = new Stage();
        window.setScene(new Scene(hb));
        window.initModality(Modality.APPLICATION_MODAL);
        btnCari.setOnAction(e -> window.close());
        window.showAndWait();
        return txtId.getText();
    }

    void save() throws IOException {
        try {
            if (readAllElement().equals("")) {
                file.write("");
            }
            file.write(readAllElement());
        } catch (Exception r) {
            System.err.println(r.getStackTrace());
        }
        Utilities.informationAlert("SAVE", "Simpan Berhasil!", "Data anda telah berhasil disimpan.");
    }

    void cari() {
        String kodeCari = dialogBox();
        ObservableList<BahanKimia> daftarBahan = tabel.getItems();
        int idx, cacah = daftarBahan.size();
        for (idx = 0; idx < cacah; idx++) {
            if (daftarBahan.get(idx).getId_bahan_kimia().equals(kodeCari)) {
                break;
            }
        }
        if (idx < cacah) {
            tabel.getSelectionModel().select(idx);
            tabel.scrollTo(idx);
            tabel.requestFocus();
            Utilities.informationAlert("PENCARIAN", "Data Ditemukan!", "");
        } else {
            Utilities.warningAlert("PENCARIAN", kodeCari + " Tidak Ditemukan!", "");
        }
    }

    void dialogBoxFilter() {
        TextField txtFilter = new TextField();
        txtFilter.setMinWidth(275);
        txtFilter.setPromptText("Keywords");
        Button btnClose = new Button("Close");
        HBox hb = new HBox(5, txtFilter, btnClose);
        hb.setPadding(new Insets(15, 10, 10, 10));
        Stage window = new Stage();
        window.setScene(new Scene(hb));
        window.initModality(Modality.APPLICATION_MODAL);
        btnClose.setOnAction(e -> window.close());
        FilteredList<BahanKimia> dataTersaring = new FilteredList<>(tabel.getItems(), b -> true);
        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            dataTersaring.setPredicate(bahanKimia -> {
                if (bahanKimia.getId_bahan_kimia().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if (bahanKimia.getNama().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if (bahanKimia.getBentuk().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if (bahanKimia.getSifat_zat().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if (bahanKimia.getVolume().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if (bahanKimia.getTanggal_terima().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else {
                    return false;
                }
            });
            tabel.setItems(dataTersaring);
        });
        window.setTitle("Tulis ID/Nama/Volume/Bentuk/Sifat Zat/Tanggal Terima");
        window.setX(500);
        window.setY(100);
        window.showAndWait();
        dataTersaring.setPredicate(t -> true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
