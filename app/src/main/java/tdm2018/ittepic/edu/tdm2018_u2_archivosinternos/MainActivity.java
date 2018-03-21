package tdm2018.ittepic.edu.tdm2018_u2_archivosinternos;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText textBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBox = (EditText) findViewById(R.id.editText);

    }
    public void onClickSave(View view) {

        FileOutputStream flujo=null;
        OutputStreamWriter escritor = null;
        try
        {
            File ruta = Environment.getExternalStorageDirectory();
            File fichero = new File(ruta.getAbsolutePath(), "ficheroPrueba.txt");
            flujo =new FileOutputStream(fichero);
            escritor=new OutputStreamWriter(flujo);
            escritor.write(textBox.getText().toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        finally
        {
            try {
                if(escritor!=null)
                    escritor.close();
                Toast.makeText(getBaseContext(), "Archivo guardado!", Toast.LENGTH_SHORT).show();
                textBox.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onClickLoad(View view) {
        InputStreamReader flujo=null;
        BufferedReader lector=null;
        try
        {
            File ruta = Environment.getExternalStorageDirectory();
            File fichero = new File(ruta.getAbsolutePath(), "ficheroPrueba.txt");
            flujo= new InputStreamReader(new FileInputStream(fichero));
            lector= new BufferedReader(flujo);
            String texto = lector.readLine();
            while(texto!=null)
            {
                textBox.setText(texto);
                texto=lector.readLine();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try {
                if(lector!=null)
                    lector.close();
                Toast.makeText(getBaseContext(), "Archivo cargado!",
                        Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
