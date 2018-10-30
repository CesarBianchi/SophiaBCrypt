/*
 * Copyright (C) 2018 CesarBianchi
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package sophiabcrypt;
import javax.swing.JOptionPane;

/**
 * This class can be validate password policy (if both are same, minimal length, formation)
 * By the way, SophiaBCrypt just allow password with:
 * 1 - That has at least 1 lowercase or uppercase letter
 * 2 - That has at least 1 number
 * 3 - That has at least 1 Special Char
 * 4 - That has at least 8 Chars of length
 * @author CesarBianchi
 * @since Sep/2018
*/
public class SbcPswControl {
    private String cPsw1 = new String();
    private String cPsw2 = new String();
    private String cOper = new String();
    private int MinLength = 8;
    
    /**
     * Is the same a "Constructor" method, receives a two passwords inputed by user
     * @param cP1 The first password inputed
     * @param cP2 The second password inputed
     * @param cOp The Operation (ENC or DEC)
     * @author CesarBianchi
     * @since Sep/2018
    */
    public void init(String cP1,String cP2,String cOp){
        cPsw1 = cP1.trim();
        cPsw2 = cP2.trim();
        cOper = cOp;
    }
    
    /**
     * This method returns the First password inputed by user
     * @author CesarBianchi
     * @return cPsw1 The First password inputed, without validations     
     * @since Sep/2018
    */
    private String getPsw1(){
        return cPsw1;
    }
    
    /**
     * This method returns the Second password inputed by user
     * @author CesarBianchi
     * @return cPsw2 The Second password inputed, without validations     
     * @since Sep/2018
    */
    private String getPsw2(){
        return cPsw2;
    }
    
    /**
     * This method provide all validations about passwords inputed, by according with SophiaBCrypt password policy.
     * Details about password policy, read description of this class
     * @author CesarBianchi
     * @return lReturn True for valid password or False to invalid password
     * @since Sep/2018
    */
    public boolean IsValidPsw(){        
        boolean lReturn = false;
        String cMsgAlert = new String();
        
        if (cOper == "ENC"){
            //1 - Verifica se sao iguais
            if (PswsAreEquals()){

                //2 - Verifica se obedecem a complexidade minima de TAMANHO
                if (PswMinLength()){

                    //3 - Verifica se obedecem a complexidade minima de NUMEROS
                    if (PswContainNumber()){

                        //4 - Verifica se obedecem a complexidade minima de LETRAS ou CARACTERES
                        if (PswContainChars()){

                            lReturn = true;
                        }else{
                            //Mensagem de psw sem CARACTERES
                            cMsgAlert = "As senhas digitadas precisam ter ao menos uma letra";
                            lReturn = false;   
                        }
                    }else{
                        //Mensagem de psw sem numeros
                        cMsgAlert = "As senhas digitadas precisam ter ao menos um numero";
                        lReturn = false;
                    }
                }else{
                    //Mensagem de psw tamanho minimo
                    cMsgAlert = "As senhas digitadas nao obedecem ao comprimento minimo necessario (" + Integer.toString(MinLength) + " caracteres)"  ;
                    lReturn = false;
                }
            }else{
                //Mensagem de psw diferente
                cMsgAlert = "Senhas não são iguais";
                lReturn = false;
            }
        }else{
            //1 - Verifica se sao iguais
            if (PswsAreEquals()){
                lReturn = true;
            }else{
                //Mensagem de psw diferente
                cMsgAlert = "Senhas não são iguais";
                lReturn = false;
            }
        }
        
        if (lReturn == false){
            JOptionPane.showMessageDialog(null,cMsgAlert,"Senha invalida",JOptionPane.ERROR_MESSAGE);
        }
        return lReturn;
    }
   
    /**
     * This method validate if all passwords (1 and 2) are equals
     * @author CesarBianchi
     * @return lReturn True for equals passwords or False to diferent passwords
     * @since Sep/2018
    */
    private boolean PswsAreEquals(){
        boolean lReturn = false;
        lReturn = getPsw1().equals(getPsw2());
        return lReturn;
    }
    
    /**
     * This method validate the minimal length of password
     * @author CesarBianchi
     * @return lReturn True for Ok length password or False to not ok length passwords
     * @since Sep/2018
    */
    private boolean PswMinLength(){
        boolean lReturn = false;
        lReturn = getPsw1().length() >= MinLength;
        return lReturn;
    }
    
    /**
     * This method validate if password contains numbers
     * @author CesarBianchi
     * @return lReturn True for Ok password or False to not ok passwords
     * @since Sep/2018
    */
    private boolean PswContainNumber(){
        boolean lReturn = false;
        int nI = 0;
        
        for (nI = 0;nI <= 9;nI++){
            if (getPsw1().contains(Integer.toString(nI))){
                lReturn = true;
                break;
            }
        }
        return lReturn;
    }
    
    /**
     * This method validate if password contains chars
     * @author CesarBianchi
     * @return lReturn True for Ok password or False to not ok passwords
     * @since Sep/2018
    */
    private boolean PswContainChars(){
        boolean lReturn = false;
        String alfabeto = new String();
        int nI = 0;

        alfabeto = "abcdefghijklmnopqrstuvxwyz.,/!@#$%ˆ*()?><|±-+{}";
        for (nI = 0; nI <= alfabeto.length()-1 ; nI++){
            
            //Maiusculo
            if (getPsw1().contains(alfabeto.substring(nI,nI+1).toUpperCase())){
                lReturn = true;
                break;
            }
            
            //minusculo
            if (getPsw1().contains(alfabeto.substring(nI,nI+1).toLowerCase())){
                lReturn = true;
                break;
            }
        }
        
        return lReturn;
    }
}