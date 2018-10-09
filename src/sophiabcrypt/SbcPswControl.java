package sophiabcrypt;

//@author cesarbianchi

import javax.swing.JOptionPane;

public class SbcPswControl {
    private String cPsw1 = new String();
    private String cPsw2 = new String();
    private String cOper = new String();
    private int MinLength = 8;
    
    
    public void init(String cP1,String cP2,String cOp){
        cPsw1 = cP1.trim();
        cPsw2 = cP2.trim();
        cOper = cOp;
    }
    
    private String getPsw1(){
        return cPsw1;
    }
    
    private String getPsw2(){
        return cPsw2;
    }
    
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
    
    private boolean PswsAreEquals(){
        boolean lReturn = false;
        lReturn = getPsw1().equals(getPsw2());
        return lReturn;
    }
    
    private boolean PswMinLength(){
        boolean lReturn = false;
        lReturn = getPsw1().length() >= MinLength;
        return lReturn;
    }
    
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