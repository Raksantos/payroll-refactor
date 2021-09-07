package memento;

import models.Company;
import java.util.Stack;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

public class MementoCompany {
    private Stack<String> undo;

    private Stack<String> redo;

    public MementoCompany(){
        this.undo = new Stack<>();;
        this.redo = new Stack<>();;
    }

    public void save(Company company){
        this.undo.push(this.saveState(company));
    }

    public Company undo(Company company){
        if(!this.undo.empty()){
            redo.push(this.saveState(company));
            String state = undo.pop();
            Company oldCompanyState = this.restoreState(state);
            return oldCompanyState;
        }
        System.out.println("\n\nNothing to undo!\n\n");
        return company;
    }

    public Company redo(Company company){
        if(!this.redo.isEmpty()){
            undo.push(this.saveState(company));
            String state = redo.pop();
            Company oldCompanyState = this.restoreState(state);
            return oldCompanyState;
        }
        System.out.println("\n\nNothing to redo!\n\n");
        return company;
    }

    private String saveState(Company company){
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(company);
            oos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        }catch(Exception err){
            err.printStackTrace();
            System.out.println("\n\nCouldn't save the state.\n\n");
            return "";
        }
    }

    private Company restoreState(String state){
        try{
            byte[] data = Base64.getDecoder().decode(state);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            return (Company) ois.readObject();
        }catch(Exception err){
            err.printStackTrace();
            System.out.println("\n\nCouldn't restore the state.\n\n");
            return null;
        }
    }
}
