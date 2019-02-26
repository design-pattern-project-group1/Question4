package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;

public class Controller
{
    public Button typo_button;

    @FXML
    private TreeView<File> folder_tree;

    @FXML
    private TextField typo_text_field;

    public javafx.scene.control.TextArea areaText;
    private WordText text = new WordText();
    CareTaker careTaker = new CareTaker();
    private TextAdapter textAdapter = new TextAdapter();
    private Model model;
    private int index = 0;

    public Controller(Model model)
    {
        this.model = model;
    }

    @FXML
    private void onOpen()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        File file = fileChooser.showOpenDialog(null);

        FileTree rootItem = new FileTree(file);
        folder_tree.setRoot(rootItem);

        folder_tree.getSelectionModel().selectedItemProperty().addListener((v, old_v, new_v) ->
        {
            if (new_v != null)
            {
                try {
                    text.setPath(new_v.getValue().toPath());
                    text.add_words();
                    display_text();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        text_listener();
    }

    @FXML
    private void text_listener()
    {
        WordBuilder builder = new WordBuilder();

        areaText.textProperty().addListener( (obs,old_v,new_v) ->
        {
            Word current_word = null;
            char new_character;

            if( new_v.equals("") || old_v.equals(""))
            {
                builder.clear();
            }
            else if(new_v.length() < old_v.length())
            {
                builder.remove_last_char();
            }
            else {

                new_character = new_v.charAt(new_v.length()-1);

                if (!(new_character == ' '))
                {
                    System.out.println(new_character);
                    builder.append(new_character);
                }
                else
                {
                    try
                    {
                        current_word = builder.build();
                        careTaker.add(text.add_word(current_word));
                        display_text();
                        index++;
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML
    private void onSave()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Save Status:");
        alert.setTitle("Save");

        if(model.save(text))
            alert.setContentText("File saved successfully!");
        else
            alert.setContentText("Error while saving file");

        alert.show();

    }

    @FXML
    private void onClose()
    {
        model.quit();
    }

    @FXML
    private void onUndo()
    {
        index--;
        text.restoreMemento(careTaker.get(index));
        display_text();
    }

    @FXML
    private void onRedo()
    {
        text.restoreMemento(careTaker.get(index));
        display_text();
        index++;
    }

    @FXML
    private void onAbout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("About");
        alert.setContentText("This is a simple text editor EditorApp");
        alert.show();
    }

    @FXML
    private void onQuit()
    {
        model.quit();
    }

    private void display_text()
    {
        areaText.clear();
        areaText.setText(textAdapter.get_text(text));
        typo_text_field.clear();
        typo_text_field.setText("Number of words with typos: " + model.number_of_typo(text));
    }


}

