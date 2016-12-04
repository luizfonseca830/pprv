/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sfcs.view.beans.amcharts;

import java.io.Serializable;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author msantarelle
 */
@ManagedBean
@RequestScoped
public class AmChartsBean implements Serializable {   

    public String calculateGraph() {
        System.out.println("entrei no método calculate");

        final StringBuilder buf = new StringBuilder();
        Random r = new Random();

        int y1 = r.nextInt(100);
        int y2 = r.nextInt(100);
        int y3 = r.nextInt(100);
        buf.append("[{");
        buf.append("x: ").append(0).append(",");
        buf.append("y1: ").append(y1).append(",");
        buf.append("y2: ").append(y2).append(",");
        buf.append("y3: ").append(y3);
        buf.append("}]");                  
        
        String result = "y" + 1 + ":";
        System.out.println("Result. " + result);
        
        return buf.toString();
    }

    public String[] getTitle (){
        return new String[]{"title01","title02","title03"};
    }
}
