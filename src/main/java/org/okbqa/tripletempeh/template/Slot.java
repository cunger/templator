package org.okbqa.tripletempeh.template;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author cunger
 */
public class Slot {
    
    String var;
    String form;
    String type;
    String value;
    
    public Slot(String var,String f) {
        this(var,f,"UNSPEC","");
    }
    public Slot(String var,String f,String t) {
        this(var,f,t,"");
    }
    
    public Slot(String var,String f, String t,String val) {
        this.var   = var;
        this.form  = f;
        this.type  = t;
        this.value = val;
    }
    
    // Getter 
    
    public String getVar() {
        return var;
    }
    public String getForm() {
        return form;
    }
    public String getType() {
        return type;
    }
    
    // Setter 
    
    public void setValue(String v) {
        value = v;
    }
    
    // JSON
    
    public JSONObject toJSON() {
        
        JSONObject slot = new JSONObject();
        
        slot.put("var",var);
        slot.put("form",form);
        slot.put("type",type);
        slot.put("value",value);
        
        return slot;
    }    
    public List<JSONObject> toListofJSONObjects() {
        
        List<JSONObject> triples = new ArrayList<>();
        
        // type
        JSONObject t = new JSONObject();
        t.put("s",var);
        t.put("p","is");
        t.put("o",type);
        triples.add(t);
        // form
        if (!form.isEmpty()) {
            JSONObject f = new JSONObject();
            f.put("s",var);
            f.put("p","verbalization");
            f.put("o",form); 
            triples.add(f);
        }
        // value 
        if (!value.isEmpty()) {
            JSONObject v = new JSONObject();
            v.put("s",var);
            v.put("p","value");
            v.put("o",value);
            triples.add(v);
        }
        
        return triples;
    }
    
    // Show 
    
    @Override
    public String toString() {
        String out = var + " " + form + " (" + type + ")";
        if (!value.isEmpty()) {
            out += " = " + value;
        }
        return out;
    }
    
    // hash and equals
    
    @Override
    public int hashCode() {
        return var.hashCode()+form.hashCode()+type.hashCode()+value.hashCode();
    }

    @Override
    public boolean equals(Object other){
           if (!(other instanceof Slot)) return false;
           if (other == this) return true;
           Slot s = (Slot) other;
           return (var.equals(s.var) && form.equals(s.form) && type.equals(s.type)) && value.equals(s.value);
    }
    
}
