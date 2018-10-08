package pointage.itm.maxime.pointageapp;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class Writer {
    private String textfile;
    private User user;
    private PrintWriter writer;
    public String path;

    public Writer(User user) {
        this.textfile = "userPointage.txt";
        this.user = user;
        this.path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/UserPoint";
        File dir = new File(path);
        dir.mkdirs();
    }

    public String getTextfile() {
        return textfile;
    }

    public void writeFile(){

        File fichier = new File(path+"/"+this.getTextfile());
        String [] userInfos =(user.getID() + ";" + user.getHeure()).split("");
        Save(fichier, userInfos);
    }

    public static void Save(File file, String[] data)
    {
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        try
        {
            try
            {
                for (int i = 0; i<data.length; i++)
                {
                    fos.write(data[i].getBytes());
                    if (i < data.length-1)
                    {
                        fos.write("\n".getBytes());
                    }
                }
            }
            catch (IOException e) {e.printStackTrace();}
        }
        finally
        {
            try
            {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public static String[] Load(File file)
    {
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String test;
        int anzahl=0;
        try
        {
            while ((test=br.readLine()) != null)
            {
                anzahl++;
            }
        }
        catch (IOException e) {e.printStackTrace();}

        try
        {
            fis.getChannel().position(0);
        }
        catch (IOException e) {e.printStackTrace();}

        String[] array = new String[anzahl];

        String line;
        int i = 0;
        try
        {
            while((line=br.readLine())!=null)
            {
                array[i] = line;
                i++;
            }
        }
        catch (IOException e) {e.printStackTrace();}
        return array;
    }

    public void writeInfile(){
        //On instancie un nouveau Fichier
        File fichier = new File(path+"/"+this.getTextfile());
        //On écrit dedans
        try {
            this.writer = new PrintWriter(new FileWriter(fichier,true));
            writer.write(user.getID()+";"+user.getHeure());
            writer.println();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void eraseFile(){
        File fichier = new File(this.getTextfile());
        //On efface le contenu du fichier
        try {
            this.writer = new PrintWriter(new FileWriter(fichier, false));
            writer.write("");
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
