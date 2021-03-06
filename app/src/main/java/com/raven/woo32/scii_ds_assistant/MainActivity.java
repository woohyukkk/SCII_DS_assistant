package com.raven.woo32.scii_ds_assistant;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.media.SoundPool;
import android.media.AudioManager;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {// ss
    private SoundPool soundPool;
    //private AdView mAdView;
    String cost_Value;
    String armor_Value;
    String hp_Value;
    String type_Value;
    String range_a_Value;
    String range_g_Value;
    String move_Value;
    String dmg_g_Value, dmg_a_Value;
    String atkSp_g_Value, atkSp_a_Value;
    String dps_g_Value, dps_a_Value;
    String bouns_Value;
    String hppc_Value,dpspc_Value,bdps_Value;

    int race=0;  //terran=0 protoss=1 zerg=2
    int current=0; //Terran=1x Protoss=2x Zerg=3x

    //Terran order  1.marine 2.marauder 3.reaper 4.ghost 5.hellbat   6.medivic 7.banshee 8.viking  9.raven 10.tank 11.mine    12.cyclone 13.lib      14.thor     15.BC
//Protoss order 1.zealot 2.stalker  3.sentry 4.adept 5.ob/oracle 6.dt      7.ball    8.phoenix 9.ht    10.immo 11.void    12.coloss  13.tempest  14.carrier   15.core
//Zerg          1.zerglin2.baneling 3.roach  4.queen 5.overseer  6.hydrlisk7.mutalisk8.corrup  9.infes 10.host 11.lurker  12.ravager 13.viper    14.ultrlisk  15brood lord
    int[][]   unit_shield={{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,50,80,40,70,20,80,100,60,40,100,100,150,150,150,60},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    int[][] unit_costs={{0,50, 90, 75,235, 85,110,190,190,260,300,125,185,200,375,525}
            ,{0,85,100,125, 95,125,175,225,140,275,255,260,325,450,525,175},
            {0,20,50,85,175,115,105,80,175,225,250,300,150,200,350,375}};
    int[][] unit_armor={{0,0,1,0,0,0,1,0,0,1,1,0,1,1,2,3},
            {0,1,1,1,1,0,1,1,0,0,1,0,1,2,2,1},
            {0,0,0,1,1,1,0,0,2,0,1,1,1,1,2,1}};
    int[][] unit_hp=   {{0, 45,125,60,100,135,150,140,125,140,175, 90,180,180,400,550},
            {0,100, 80,40, 70, 40, 40,100,120, 40,200,150,200,300,250,130},
            {0, 35,30,145,175,200, 90,120,200, 90,160,200,120,150,500,225}};
    int[][] unit_type=   {{0,00,10,00,30,07,11,01,01,11,01,01,11,11,16,16},{0,00,11,01,00,01,8,11,01,8,11,11,16,16,16,19},{0,00,42,10,28,10,00,00,10,18,10,10,42,18,15,15}};  // 0 light 1 Armored 2 none 3 psi 4 bio , 0 bio 1 mec 2 none 3 psi 4 massive 5 Bio-massive 6 Mec-massive 7 Bio-Mec 8 Bio-psi 9 mec-psi
    int[][] unit_range_gnd={{0,5,6,5,6,2,0,6,0,0,7,0,6,0,7, 6},{0,1,6,5,4,0,1,0,0,0,6,6,6,10,8,5},{0,1,1,4,5,0,5,3,0,0,0,9,6,0,1,10}};
    int[][] unit_range_air={{0,5,0,0,6,0,0,0,9,0,0,0,0,5,10,6},{0,0,6,5,0,0,0,0,5,0,0,6,0,15,8,0},{0,0,0,0,8,0,5,3,6,0,0,0,0,0,1, 0}};
    double[][] unit_move={{0,3.15,3.15,5.25,3.94,3.15,3.5,3.85,3.85,3.85,3.15,3.94,4.13,4.72,2.62,2.62},{0,3.15,4.13,3.15,3.5,2.62,3.94,3.15,5.95,2.62,3.15,3.5,3.15,2.62,2.62,2.62},{0,4.13,3.5,3.15,1.31,2.62,3.15,5.6,4.72,3.15,4.13,4.13,3.85,4.13,4.13,1.97}};
    int[][] unit_dmg_g={{0,6,5,4,10,18, 0,12, 0,0,15,0,3,0,30,8},                                                 {0,8,  10, 6,10, 0,45, 0, 0, 0, 20, 6,12,40, 5, 8},  {0,  5,20,16, 4,0,12,9, 0,0,0,20,16,0,35,20 }};
    int[][] unit_dmg_a={{0,6,0,0,10, 0, 0, 0,10,0, 0, 0,0,5,6,6},                                                 {0,0,  10, 6, 0, 0, 0, 0, 5, 0,  0, 6, 0,30, 5, 0},  {0,  0,0, 0, 9,0,12,9,14,0,0, 0, 0,0, 0,0 }};
    int[][] unit_atks_g={{0,1,2,2,1,1,0,2,0,0,1,0,1,0,2,1},                                                       {0,2,   1, 1, 1, 0, 1, 0, 0, 0,  1,1, 2, 1, 2*8, 1}, {0,  1,1, 1, 2,0,1, 1, 1,0,0, 1, 1,0, 1,1 }};
    int[][] unit_atks_a={{0,1,0,0,1,0,0,0,2,0,0,0,0,2,4,1},                                                       {0,0,   1, 1, 0, 0, 0, 0, 2, 0,  0,1, 0, 1, 2*8, 0}, {0,  0,0, 0, 1,0,1, 1, 1,0,0, 0, 1,0, 0,0 }};
    double[][] unit_atkSp_g={{0,0.61,1.07,0.79,1.07,1.43,0,0.89,0,0,0.74,0,0.1,0,0.91,0.16}, {0,0.86,   1.03,0.71,1.61,0,1.21,0,   0, 0,1.04,0.36,1.07,2.36,0.71,0.61},{0,0.5,0.59,1.43,0.71,0,0.54,1.09,   0,0,0,1.43,1.14,0,0.61,0.79}};
    double[][] unit_atkSp_a={{0,0.61,0,0,      1.07,0   ,0,0   ,1.43,   0,0,0,0  ,1.29,2.14,0.16}, {0,   0,   1.03,0.71,   0,0,0,0,0.79, 0,   0,0.36,   0,2.36,0.71,0},{0,  0,0,   0,0.71,0,0.54,1.09,1.36,0,0,   0,   0,0,0,0}};
    int[][] unit_bouns_type_g={{0,0,2 , 0, 1, 0,0,0,0,0,2,   0,2, 0,0,0},                       {0,0,   2,0,1,0,0,0, 0, 0,2,2, 0,0,0,0},                               {0,  0,1,0,0,0,0,0,0,0,0, 2,0,0,0,0}};    //0 none 1 light 2 Armored 3 Bio 4 Mec 5 Psi 6 massive
    int[][] unit_bouns_dmg_g ={{0,0,5 , 0,10, 0,0,0,0,0,10,  0,2, 0,0,0},                       {0,0,   4,0,12,0,0,0,0,0,30,4, 0,0,0,0},                               {0,  0,15,0,0,0,0,0,0,0,0, 10,0,0,0,0}};
    int[][] unit_bouns_type_a={{-1,0,0 , 0, 1, 0,0,0,2,0,0,   0,0,0, 1,0},                      {0,0,   2,0,0,0,0,0,1, 0,0, 2,0, 6,0,0},                               {0,  0,0,0,0,0,0,0,6,0,0,0,0,0,0,0}};    //0 none 1 light 2 Armored 3 Bio 4 Mec 5 Psi 6 massive
    int[][] unit_bouns_dmg_a ={{-1,0,0 , 0,10, 0,0,0,4,0,0,   0,0,0, 6,0},                      {0,0,   4,0,0,0,0,0,5, 0,0, 4,0,22,0,0},                               {0,  0,0,0,0,0,0,0,6,0,0, 0,0,0,0,0}};

    int sound_terran,sound_protoss,sound_zerg;
    int[][] sound_unit=new int[3][16];
    int count=0;
    TextView cost;
    TextView armor;
    TextView hp;
    TextView type;
    TextView range_g;
    TextView range_a;
    TextView move;
    TextView dmg_g,dmg_a;
    TextView atkSp_g,atkSp_a;
    TextView dps_g,dps_a;
    TextView bouns;
    TextView hppc,dpspc_g,dpspc_a,bdps,shield;



    ImageButton unit_1;
    ImageButton unit_2;
    ImageButton unit_3;
    ImageButton unit_4;
    ImageButton unit_5;
    ImageButton unit_6;
    ImageButton unit_7;
    ImageButton unit_8;
    ImageButton unit_9;
    ImageButton unit_10;
    ImageButton unit_11;
    ImageButton unit_12;
    ImageButton unit_13;
    ImageButton unit_14;
    ImageButton unit_15;


    ImageButton upgrade1;
    ImageButton upgrade2;
    ImageButton upgrade3;
    ImageButton skill1;
    ImageButton skill2;
    ImageButton skill3;
    Button button_T,button_P,button_Z;

    boolean hellbatOFF=false;
    boolean turret=false;
    boolean lib_defON=false;
    boolean toggleFlag=false;
    boolean ob_oracle=true;
    boolean currentChange=false;
    private TextView mTextMessage;
    LinearLayout mainBackground;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//------------------Sound
        soundPool = new SoundPool(100, AudioManager.STREAM_MUSIC, 0);
        sound_terran=soundPool.load(this,R.raw.touch_terran,5);
        sound_protoss=soundPool.load(this,R.raw.touch_protoss,5);
        sound_zerg=soundPool.load(this,R.raw.touch_zerg,5);
        sound_unit[0][1]=soundPool.load(this,R.raw.t_marine,5);


        //--------------------------AD
        //mAdView = (AdView) findViewById(R.id.adView);
        //AdRequest adRequest = new AdRequest.Builder().build();
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice("22EDCC21E31ABCDF011984A55C5A56E6").build();// test ad

       // mAdView.loadAd(adRequest);

        cost=(TextView) findViewById(R.id.textView_cost);
        armor=(TextView) findViewById(R.id.textView6_armor);
        hp=(TextView) findViewById(R.id.textView_hp);
        type=(TextView) findViewById(R.id.textView_type);
        range_a=(TextView) findViewById(R.id.textView_range_air);
        range_g=(TextView) findViewById(R.id.textView_range_gnd);
        move=(TextView) findViewById(R.id.textView_move);
        dmg_a=(TextView) findViewById(R.id.textView_dmg_a);
        dmg_g=(TextView) findViewById(R.id.textView_dmg_g);
        atkSp_g=(TextView) findViewById(R.id.textView_atkSp_g);
        atkSp_a=(TextView) findViewById(R.id.textView_atkSp_a);
        dps_g=(TextView) findViewById(R.id.textView_dps_g);
        dps_a=(TextView) findViewById(R.id.textView_dps_a);
        bouns=(TextView) findViewById(R.id.textView_bouns);
        hppc=(TextView) findViewById(R.id.textView_hppc);
        dpspc_g=(TextView) findViewById(R.id.textView_dpspc_g);
        dpspc_a=(TextView) findViewById(R.id.textView_dpspc_a);
        bdps=(TextView) findViewById(R.id.textView_bdps);
        shield=(TextView) findViewById(R.id.textView_shield);


// race buttons ctrl
        button_T=(Button)findViewById(R.id.Terren);
        button_T.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                race=0;
                soundPool.play(sound_terran, 0.7F, 0.7F, 0, 0, 1.0F);
                Log.d("race"," = 0");
                updateUI();
            }
        });

        button_P=(Button)findViewById(R.id.Protoss);
        button_P.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                race=1;
                soundPool.play(sound_protoss, 1.0F, 1.0F, 0, 0, 1.0F);
                Log.d("race"," = 1");
                updateUI();
            }
        });

        button_Z=(Button)findViewById(R.id.Zerg);
        button_Z.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                race=2;
                soundPool.play(sound_zerg, 0.3F, 0.3F, 0, 0, 1.0F);
                Log.d("race"," = 2");
                updateUI();
            }
        });











        //upgrades
        upgrade1=(ImageButton)findViewById(R.id.imageButton_upgrade1);
        upgrade1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(race==0){
                    if(current==1){//marine-shield

                        hp.setText("55");
                        hppc.setText(Double.toString(55.0/45));
                    }
                    else if(current==5){
                        if(hellbatOFF==false) {
                            bouns.setText("Gnd-Lig+12");
                            bdps.setText(Double.toString((12+18)/1.43));
                            // dpspc_g.setText(Double.toString(((12+18)/1.43)/90));
                        }else {
                            bouns.setText("Gnd-Lig+11");
                            bdps.setText(Double.toString((8+6+5)/1.79));
                            //dpspc_g.setText(Double.toString(((8+6)/1.79)/90));
                        }
                    }else if(current==13){
                        if(lib_defON==true)
                            range_g.setText("9");

                    }
                }else if(race==1){
                    if(current==1){
                        move.setText("4.13");                    //zealot charge
                    }else if(current==4){
                        atkSp_g.setText("1.11");            //adept
                        double tempdps=(Math.round (     (unit_dmg_g[race][current]*unit_atks_g[race][current] )/ 1.1*100)  ) *0.01;;
                        dps_g.setText(Double.toString(tempdps))  ;
                        tempdps=  Math.round((( (unit_dmg_g[race][current]+unit_bouns_dmg_g[race][current])*unit_atks_g[race][current] )/ 1.1)*100) *0.01 ;
                        bdps.setText(Double.toString(tempdps));


                    }else if(current==8){//phoenix
                        range_a.setText("7");
                    }else if(current==12){//colocuss
                        range_g.setText("9");
                    }else if(current==5){//colocuss
                        move.setText("3.94");
                    }else if(current==15){ //mothership
                        int hp_=350,shield_=350,dmg_g_=6,dmg_a_=6,armor_=2,bonus=0,range=7,cost_=900;
                        double atk_sp_g=1.58,atk_sp_a=1.58,movement=2.62;
                        cost.setText(Integer.toString(cost_));
                        hp.setText(Integer.toString(hp_));
                        armor.setText(Integer.toString(armor_));
                        shield.setText("(" + Integer.toString(shield_)+")");
                        type.setText("Arm-Mec-Ma");
                        range_a.setText(Integer.toString(range));
                        range_g.setText(Integer.toString(range));
                        move.setText(Double.toString(movement));
                        dmg_a.setText(Integer.toString(dmg_a_));
                        dmg_g.setText(Integer.toString(dmg_g_));
                        atkSp_g.setText(Double.toString(atk_sp_g));
                        atkSp_a.setText(Double.toString(atk_sp_a));
                        double tempdps=  Math.round((( (dmg_g_+0)*1.0 )/ atk_sp_g)*100) *0.01 ;
                        dps_g.setText(Double.toString(3.8));
                        dps_a.setText(Double.toString(3.8));
                        bouns.setText("N/A");
                        hppc.setText(Double.toString((hp_+shield_)*1.0/cost_));
                        dpspc_g.setText(Double.toString(   tempdps/cost_ ));
                        dpspc_a.setText(Double.toString(   tempdps/cost_ ));
                        tempdps=  Math.round((( (dmg_g_+bonus)*1 )/ atk_sp_g)*100) *0.01 ;
                        //bdps.setText(Double.toString(tempdps));
                    }
                }else if(race==2){
                    if (current==1){
                        int dmg_g_=6;
                        double atk_sp_g=0.35;
                        atkSp_g.setText(Double.toString(atk_sp_g));
                        double tempdps=  Math.round((( (dmg_g_+0)*1.0 )/ atk_sp_g)*100) *0.01 ;
                        dps_g.setText(Double.toString(tempdps));
                    }else if(current==6){
                        move.setText("3.94");
                        range_g.setText("6");
                        range_a.setText("6");
                    }else if(current==14){
                        armor.setText("4");
                    }
                }


            }
        });

        upgrade2=(ImageButton)findViewById(R.id.imageButton_upgrade2);
        upgrade2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(race==0){
                    if(current==1 || current == 2){//marine/maruder -stimpack  150% speed
                        double tempSp_g= unit_atkSp_g[race][current];
                        double tempSp_a= unit_atkSp_a[race][current];
                        double tempDps_g,tempDps_a,tempbdps_g=0.0,tempbdps_a=0.0;
                        tempSp_g=tempSp_g*2/3;
                        tempSp_a=tempSp_a*2/3;
                        tempDps_g=unit_dmg_g[race][current]*unit_atks_g[race][current]/tempSp_g;
                        tempDps_a=unit_dmg_a[race][current]*unit_atks_a[race][current]/tempSp_a;

                        atkSp_g.setText(Double.toString(  (Math.round(tempSp_g*100))*0.01));
                        atkSp_a.setText(Double.toString(  (Math.round(tempSp_a*100))*0.01));
                        dps_g.setText(Double.toString(  (Math.round(tempDps_g*100))*0.01));
                        dps_a.setText(Double.toString(  (Math.round(tempDps_a*100))*0.01));

                        dpspc_g.setText(Double.toString(Math.round(tempDps_g/unit_costs[race][current]*100)*0.01      ));
                        dpspc_a.setText(Double.toString(Math.round(tempDps_a/unit_costs[race][current]*100)*0.01      ));


                        if(unit_bouns_type_g[race][current]>0)
                            tempbdps_g= Math.round((( (unit_dmg_g[race][current]+ unit_bouns_dmg_g[race][current]  )   *unit_atks_g[race][current] )/ tempSp_g)*100) *0.01 ;
                        else if (unit_bouns_type_a[race][current]>0)
                            tempbdps_a= Math.round((( (unit_dmg_a[race][current]+ unit_bouns_dmg_a[race][current]  )   *unit_atks_a[race][current] )/ tempSp_a)*100) *0.01 ;

                        if(tempbdps_g>tempbdps_a)
                            bdps.setText(Double.toString(tempbdps_g));
                        else
                            bdps.setText(Double.toString(tempbdps_a));

                        move.setText("4.73");

                    }else if(current==9 && turret==true){
                        // dmg_g.setText("23");
                        //dmg_a.setText("23");
                        //dps_g.setText("40.35");
                        //dps_a.setText("40.35");
                    }
                }else if(race==1){
                    if(current==6 || current==9){//archon
                        int hp_=10,shield_=350,dmg_g_=25,dmg_a_=25,armor=0,bonus=10,range=3,cost_=275;
                        double atk_sp_g=1.25,atk_sp_a=1.25,movement=3.94;
                        cost.setText(Integer.toString(cost_));
                        hp.setText(Integer.toString(hp_));
                        shield.setText("(" + Integer.toString(shield_)+")");
                        type.setText("Psi-Ma");
                        range_a.setText(Integer.toString(range));
                        range_g.setText(Integer.toString(range));
                        move.setText(Double.toString(movement));
                        dmg_a.setText(Integer.toString(dmg_a_));
                        dmg_g.setText(Integer.toString(dmg_g_));
                        atkSp_g.setText(Double.toString(atk_sp_g));
                        atkSp_a.setText(Double.toString(atk_sp_a));
                        double tempdps=  Math.round((( (dmg_g_+0)*1 )/ atk_sp_g)*100) *0.01 ;
                        dps_g.setText(Double.toString(tempdps));
                        dps_a.setText(Double.toString(tempdps));
                        bouns.setText("Bio+10");
                        hppc.setText(Double.toString((hp_+shield_)/cost_));
                        dpspc_g.setText(Double.toString(tempdps/cost_));
                        dpspc_a.setText(Double.toString(tempdps/cost_));
                        tempdps=  Math.round((( (dmg_g_+bonus)*1 )/ atk_sp_g)*100) *0.01 ;
                        bdps.setText(Double.toString(tempdps));







                    }

                }else if(race==2){
                    if (current==1){
                        move.setText("6.58");
                    }
                }


            }
        });

        upgrade3=(ImageButton)findViewById(R.id.imageButton_upgrade3);
        upgrade3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


            }
        });

        skill1=(ImageButton)findViewById(R.id.imageButton_skill1);
        skill1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(race==0){
                    hellbatOFF=true;
                    count++;
                    if(current==1 || current == 2) {//marine/maruder -stimpack  150% speed
                        double tempSp_g = unit_atkSp_g[race][current];
                        double tempSp_a = unit_atkSp_a[race][current];
                        double tempDps_g, tempDps_a, tempbdps_g = 0.0, tempbdps_a = 0.0;
                        tempSp_g = tempSp_g * 2 / 3;
                        tempSp_a = tempSp_a * 2 / 3;
                        tempDps_g = unit_dmg_g[race][current] * unit_atks_g[race][current] / tempSp_g;
                        tempDps_a = unit_dmg_a[race][current] * unit_atks_a[race][current] / tempSp_a;

                        atkSp_g.setText(Double.toString((Math.round(tempSp_g * 100)) * 0.01));
                        atkSp_a.setText(Double.toString((Math.round(tempSp_a * 100)) * 0.01));
                        dps_g.setText(Double.toString((Math.round(tempDps_g * 100)) * 0.01));
                        dps_a.setText(Double.toString((Math.round(tempDps_a * 100)) * 0.01));

                        dpspc_g.setText(Double.toString(Math.round(tempDps_g / unit_costs[race][current] * 100) * 0.01));
                        dpspc_a.setText(Double.toString(Math.round(tempDps_a / unit_costs[race][current] * 100) * 0.01));


                        if (unit_bouns_type_g[race][current] > 0)
                            tempbdps_g = Math.round((((unit_dmg_g[race][current] + unit_bouns_dmg_g[race][current]) * unit_atks_g[race][current]) / tempSp_g) * 100) * 0.01;
                        else if (unit_bouns_type_a[race][current] > 0)
                            tempbdps_a = Math.round((((unit_dmg_a[race][current] + unit_bouns_dmg_a[race][current]) * unit_atks_a[race][current]) / tempSp_a) * 100) * 0.01;

                        if (tempbdps_g > tempbdps_a)
                            bdps.setText(Double.toString(tempbdps_g));
                        else
                            bdps.setText(Double.toString(tempbdps_a));

                        move.setText("4.73");
                    }else if(current==5) {  //hellbat-motor mode
                        hp.setText("90");
                        type.setText("Light-Mec");
                        range_a.setText("0");
                        range_g.setText("5");
                        move.setText("5.95");
                        dmg_a.setText("0");
                        dmg_g.setText("8");
                        atkSp_g.setText("1.79");
                        atkSp_a.setText("0");
                        dps_g.setText("4.47");
                        dps_a.setText("0");
                        bouns.setText("Gnd-Lig+6");
                        hppc.setText(Double.toString(90.0/90));
                        dpspc_g.setText(Double.toString(4.47/90));
                        dpspc_a.setText("0");
                        bdps.setText(Double.toString(14.0/1.79));

                    }else if(current == 8){// viking_assault
                        hp.setText("125");

                        type.setText("Armored-Mec");
                        range_a.setText("0");
                        range_g.setText("6");
                        move.setText("3.15");
                        dmg_a.setText("0");
                        dmg_g.setText("12");
                        atkSp_g.setText("0.71");
                        atkSp_a.setText("0");
                        dps_g.setText("16.9");
                        dps_a.setText("0");
                        bouns.setText("Mec+8");
                        hppc.setText(Double.toString(125.0/190));
                        dpspc_g.setText(Double.toString(16.9/190));
                        dpspc_a.setText("0");
                        bdps.setText(Double.toString((12+8)/0.71));


                    }else if(current == 9){// Raven turret
                        turret=true;
                        cost.setText("0");
                        hppc.setText("0");
                        hp.setText("150");
                        armor.setText("1+2");
                        type.setText("Armored-Mec-Strc");
                        range_a.setText("6");
                        range_g.setText("6");
                        move.setText("0");


                        dmg_g.setText("18");
                        dmg_a.setText("18");
                        dps_g.setText("31.57");
                        dps_a.setText("31.57");

                        atkSp_g.setText("0.57");
                        atkSp_a.setText("0.57");


                        dpspc_g.setText("0");
                        dpspc_a.setText("0");



                        bouns.setText("N/A");
                    }else if(current == 10){// tank siege

                        hp.setText("175");
                        armor.setText("1");
                        type.setText("Armored-Mec");
                        range_a.setText("0");
                        range_g.setText("13");
                        move.setText("0");


                        dmg_g.setText("40");
                        dmg_a.setText("0");
                        dps_g.setText("18.78");
                        dps_a.setText("0");

                        atkSp_g.setText("2.13");
                        atkSp_a.setText("0");

                        //hppc.setText(Double.toString(125.0/190));
                        dpspc_g.setText(Double.toString(18.78/310));
                        dpspc_a.setText("0");
                        bdps.setText(Double.toString((40+30)/2.13));

                        bouns.setText("Gnd-Arm+30");
                    }else if(current == 14){// thor HIP

                        hp.setText("400");
                        armor.setText("2");
                        type.setText("Armored-Mec-Ma");
                        range_a.setText("10");
                        range_g.setText("7");
                        move.setText("2.62");


                        dmg_g.setText("30x2");
                        dmg_a.setText("35");

                        dps_a.setText("16.3");

                        atkSp_g.setText("0.91");
                        atkSp_a.setText("2.14");

                        //hppc.setText(Double.toString(125.0/190));
                        //dpspc_g.setText(Double.toString(16.9/190));
                        dpspc_a.setText(Double.toString(16.3/400));
                        bdps.setText(Double.toString((35+15)/2.14));

                        bouns.setText("Air-Arm+15");
                    }else if(current == 13){// lib_def mode
                        lib_defON=true;

                        hp.setText("180");
                        armor.setText("1");
                        type.setText("Armored-Mec");
                        range_a.setText("0");
                        range_g.setText("5");
                        move.setText("4.72");


                        dmg_g.setText("75");
                        dmg_a.setText("0");
                        dps_g.setText("65.8");
                        dps_a.setText("0");

                        atkSp_g.setText("1.14");
                        atkSp_a.setText("0");

                        dpspc_g.setText(Double.toString(65.8/200));
                        dpspc_a.setText("0");



                        bouns.setText("N/A");
                    }
                }else if(race==1){
                    if(current==1){
                        move.setText(Double.toString(4.13+4.96));
                    }else if(current==11){//void ray
                        bouns.setText("10");
                        double tempdps=  Math.round((( (unit_dmg_g[race][current]+unit_bouns_dmg_g[race][current]+6)*unit_atks_g[race][current] )/ unit_atkSp_g[race][current])*100) *0.01 ;
                        bdps.setText(Double.toString(tempdps));
                    }
                }else if(race==2){
                    if(current==15){//brood lord swam
                        int hp_=30,shield_=0,dmg_g_=4,dmg_a_=0,atk_g_=2,atk_a_=0,armor_=0,bonus=0,range=1,cost_=0;
                        double atk_sp_g=0.46,atk_sp_a=0,movement=5.37;
                        cost.setText(Integer.toString(cost_));
                        hp.setText(Integer.toString(hp_));
                        // shield.setText("(" + Integer.toString(shield_)+")");
                        type.setText("Light-Bio");
                        armor.setText(Integer.toString(armor_));
                        range_a.setText(Integer.toString(range));
                        range_g.setText("Melee");
                        move.setText(Double.toString(movement));
                        dmg_a.setText(Integer.toString(dmg_a_));
                        dmg_g.setText(Integer.toString(dmg_g_)+"x2");
                        atkSp_g.setText(Integer.toString(atk_g_));
                        atkSp_g.setText(Integer.toString(atk_a_));
                        atkSp_g.setText(Double.toString(atk_sp_g));
                        atkSp_a.setText(Double.toString(atk_sp_a));
                        double tempdps=  Math.round((( (dmg_g_+0)*2 )/ atk_sp_g)*100) *0.01 ;
                        dps_g.setText(Double.toString(tempdps));
                        dps_a.setText(Double.toString(tempdps));
                        bouns.setText("N/A");
                        hppc.setText(Double.toString(0));
                        dpspc_g.setText(Double.toString(0));
                        dpspc_a.setText(Double.toString(0));
                        tempdps=  Math.round((( (dmg_g_+bonus)*1 )/ atk_sp_g)*100) *0.01 ;
                        //bdps.setText(Double.toString(tempdps));
                    }else if(current==10){//host spawn
                        int hp_=50,shield_=0,dmg_g_=10,dmg_a_=0,atk_g_=2,atk_a_=0,armor_=0,bonus=0,range=3,cost_=0;
                        double atk_sp_g=0.43,atk_sp_a=0,movement=2.62;
                        cost.setText(Integer.toString(cost_));
                        hp.setText(Integer.toString(hp_));
                        // shield.setText("(" + Integer.toString(shield_)+")");
                        type.setText("Light-Bio");
                        armor.setText(Integer.toString(armor_));
                        range_a.setText(Integer.toString(range));
                        range_g.setText(Integer.toString(range));
                        move.setText(Double.toString(movement));
                        dmg_a.setText(Integer.toString(dmg_a_));
                        dmg_g.setText(Integer.toString(dmg_g_));
                        atkSp_g.setText(Integer.toString(atk_g_));
                        atkSp_g.setText(Integer.toString(atk_a_));
                        atkSp_g.setText(Double.toString(atk_sp_g));
                        atkSp_a.setText(Double.toString(atk_sp_a));
                        double tempdps=  Math.round((( (dmg_g_+0)*1 )/ atk_sp_g)*100) *0.01 ;
                        dps_g.setText(Double.toString(tempdps));
                        dps_a.setText(Double.toString(tempdps));
                        bouns.setText("N/A");
                        hppc.setText(Double.toString(0));
                        dpspc_g.setText(Double.toString(0));
                        dpspc_a.setText(Double.toString(0));
                        tempdps=  Math.round((( (dmg_g_+bonus)*1 )/ atk_sp_g)*100) *0.01 ;
                        //bdps.setText(Double.toString(tempdps));
                    }else if(current==9){//infestor terran
                        int hp_=50,shield_=0,dmg_g_=8,dmg_a_=8,atk_g_=2,atk_a_=0,armor_=0,bonus=0,range=5,cost_=0;
                        double atk_sp_g=0.61,atk_sp_a=0.61,movement=1.31;
                        cost.setText(Integer.toString(cost_));
                        hp.setText(Integer.toString(hp_));
                        // shield.setText("(" + Integer.toString(shield_)+")");
                        type.setText("Light-Bio");
                        armor.setText(Integer.toString(armor_));
                        range_a.setText(Integer.toString(range));
                        range_g.setText(Integer.toString(range));
                        move.setText(Double.toString(movement));
                        dmg_a.setText(Integer.toString(dmg_a_));
                        dmg_g.setText(Integer.toString(dmg_g_));
                        atkSp_g.setText(Integer.toString(atk_g_));
                        atkSp_g.setText(Integer.toString(atk_a_));
                        atkSp_g.setText(Double.toString(atk_sp_g));
                        atkSp_a.setText(Double.toString(atk_sp_a));
                        double tempdps=  Math.round((( (dmg_g_+0)*1 )/ atk_sp_g)*100) *0.01 ;
                        dps_g.setText(Double.toString(tempdps));
                        dps_a.setText(Double.toString(tempdps));
                        bouns.setText("N/A");
                        hppc.setText(Double.toString(0));
                        dpspc_g.setText(Double.toString(0));
                        dpspc_a.setText(Double.toString(0));
                        tempdps=  Math.round((( (dmg_g_+bonus)*1 )/ atk_sp_g)*100) *0.01 ;
                        //bdps.setText(Double.toString(tempdps));
                    }else if(current==2){//bane explode
                        //  dmg_g.setText("");
                    }
                }

            }
        });

        skill2=(ImageButton)findViewById(R.id.imageButton_skill2);
        skill2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                if(race==0){
                    if(current==6){
                        move.setText("5.95");
                    }

                }else if(race==1){

                }else if(race==2){

                }


            }
        });

        skill3=(ImageButton)findViewById(R.id.imageButton_skill3);
        skill3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


            }
        });







        // units
        /////////////////////////////////////COST
        unit_1=(ImageButton)findViewById(R.id.imageButton1);
        unit_1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                 toggleFlag=false;int i=1;
               
                    
                 if(current!=i){                     currentChange=true;                     current=i;                 }else                     currentChange=false;; updateUI();
                if(race==0)
                    unit_1.setImageResource(R.drawable.marine_select);
                else if(race==1)
                    unit_1.setImageResource(R.drawable.zealot_select);
                else if(race==2)
                    unit_1.setImageResource(R.drawable.zergling_select);
                if(race==1)
                    shield.setText("("+ Integer.toString(unit_shield[race][i])  +")");
                else
                    shield.setText("");
                Log.d("@1-i: ", Integer.toString(i));
                cost_Value=Integer.toString(unit_costs[race][i]);
                armor_Value=Integer.toString(unit_armor[race][i]);
                cost.setText(cost_Value);
                armor.setText(armor_Value);
                hp_Value=Integer.toString(unit_hp[race][i]);
                hp.setText(hp_Value);
                type_Value=typeTrans(unit_type[race][i]); Log.d("@1",type_Value);
                type.setText( type_Value);
                range_a_Value=Integer.toString(unit_range_air[race][i]);
                range_a.setText( range_a_Value);
                range_g_Value=Integer.toString(unit_range_gnd[race][i]);

                if(unit_range_gnd[race][i]==1)                     range_g.setText( "Melee");                 else                     range_g.setText( range_g_Value);
                move_Value=Double.toString(unit_move[race][i]);
                move.setText( move_Value);

                dmg_g_Value=Integer.toString(unit_dmg_g[race][i]);
                if(unit_atks_g[race][i]>=2)
                    dmg_g_Value=dmg_g_Value+"x"+ Integer.toString(unit_atks_g[race][i]);
                dmg_g.setText( dmg_g_Value);
                dmg_a_Value=Integer.toString(unit_dmg_a[race][i]);
                if(unit_atks_a[race][i]>=2)
                    dmg_a_Value=dmg_a_Value+"x"+ Integer.toString(unit_atks_a[race][i]);
                dmg_a.setText( dmg_a_Value);

                atkSp_g_Value=Double.toString(unit_atkSp_g[race][i]);
                atkSp_g.setText( atkSp_g_Value);
                atkSp_a_Value=Double.toString(unit_atkSp_a[race][i]);
                atkSp_a.setText( atkSp_a_Value);
                dps_g_Value=Double.toString( (Math.round (     (unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i]*100)  ) *0.01     );
                dps_g.setText( dps_g_Value);
                dps_a_Value=Double.toString( (Math.round (     (unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i]*100)  ) *0.01     );
                dps_a.setText( dps_a_Value);
                if(unit_bouns_type_g[race][i]>0){
                    bouns_Value=bounsTrans(unit_bouns_type_g[race][i],unit_bouns_dmg_g[race][i],0);
                }else if((unit_bouns_type_a[race][i]>0)){
                    bouns_Value=bounsTrans(unit_bouns_type_a[race][i],unit_bouns_dmg_a[race][i],1);
                }
                else
                    bouns_Value="N/A";

                bouns.setText( bouns_Value);



                hppc.setText(Double.toString(  Math.round( (unit_hp[race][i]+unit_shield[race][i])*1.0/unit_costs[race][i]*100) *0.01 ));


                Double dps1,dps2;
                dps1=  Math.round(((unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])/unit_costs[race][i]*100) *0.01 ;
                dps2=  Math.round(((unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])/unit_costs[race][i]*100) *0.01 ;
                dpspc_g.setText(Double.toString(dps1));
                dpspc_a.setText(Double.toString(dps2));

                dps1=0.0;
                dps2=0.0;
                if(unit_bouns_type_g[race][i]>0)
                    dps1=  Math.round((( (unit_dmg_g[race][i]+unit_bouns_dmg_g[race][i])*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])*100) *0.01 ;
                else if(unit_bouns_type_a[race][i]>0)
                    dps2=  Math.round((( (unit_dmg_a[race][i]+unit_bouns_dmg_a[race][i])*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])*100) *0.01 ;

                if(dps1>dps2)
                    bdps.setText(Double.toString(dps1));
                else
                    bdps.setText(Double.toString(dps2));

                if(currentChange==true){// play ready sound
                    count=0;
                }else
                    count++;
                if(count==2){
                    count=0;
                    soundPool.play(sound_unit[race][current], 1.0F, 1.0F, 0, 0, 1.0F);
                }

                //--------------------------------------------upgrade control--------------------------------
                if(race==0){
                    upgrade1.setImageResource(R.drawable.marrine_shield);
                    upgrade2.setImageResource(R.drawable.marine_stimpack);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.marine_stimpack);
                    //skill2.setImageResource(R.drawable.);
                    //skill3.setImageResource(R.drawable.);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.VISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);

                }else if(race==1){
                    upgrade1.setImageResource(R.drawable.zealot_charge);
                    //upgrade2.setImageResource(R.drawable.marine_stimpack);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.zealot_charge);
                    //skill2.setImageResource(R.drawable.);
                    //skill3.setImageResource(R.drawable.);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==2){
                    upgrade1.setImageResource(R.drawable.zergling_atk);
                    upgrade2.setImageResource(R.drawable.zergling_speed);
                    //upgrade3.setImageResource(R.drawable.);
                    //skill1.setImageResource(R.drawable.zealot_charge);
                    //skill2.setImageResource(R.drawable.);
                    //skill3.setImageResource(R.drawable.);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.VISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.INVISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }

            }
        });

        unit_2=(ImageButton)findViewById(R.id.imageButton2);
        unit_2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                toggleFlag=false;int i=2;  if(current!=i){                     currentChange=true;                     current=i;                 }else                     currentChange=false;; updateUI();                if(race==1)                     shield.setText("("+ Integer.toString(unit_shield[race][i])  +")");                 else                     shield.setText("");
                if(race==0)
                    unit_2.setImageResource(R.drawable.marauder_select);
                else if(race==1)
                    unit_2.setImageResource(R.drawable.stalker_select);
                else if(race==2)
                    unit_2.setImageResource(R.drawable.baneling_select);
                cost_Value=Integer.toString(unit_costs[race][i]);
                cost.setText(cost_Value);
                armor_Value=Integer.toString(unit_armor[race][i]);
                armor.setText(armor_Value);
                hp_Value=Integer.toString(unit_hp[race][i]);
                hp.setText(hp_Value);
                type_Value=typeTrans(unit_type[race][i]);Log.d("@2",type_Value);
                type.setText(type_Value);
                range_a_Value=Integer.toString(unit_range_air[race][i]);
                range_a.setText( range_a_Value);
                range_g_Value=Integer.toString(unit_range_gnd[race][i]);
                if(unit_range_gnd[race][i]==1)                     range_g.setText( "Melee");                 else                     range_g.setText( range_g_Value);
                move_Value=Double.toString(unit_move[race][i]);
                move.setText( move_Value);
                dmg_g_Value=Integer.toString(unit_dmg_g[race][i]);
                if(unit_atks_g[race][i]>=2)
                    dmg_g_Value=dmg_g_Value+"x"+ Integer.toString(unit_atks_g[race][i]);
                dmg_g.setText( dmg_g_Value);
                dmg_a_Value=Integer.toString(unit_dmg_a[race][i]);
                if(unit_atks_a[race][i]>=2)
                    dmg_a_Value=dmg_a_Value+"x"+ Integer.toString(unit_atks_a[race][i]);
                dmg_a.setText( dmg_a_Value);

                atkSp_g_Value=Double.toString(unit_atkSp_g[race][i]);
                atkSp_g.setText( atkSp_g_Value);
                atkSp_a_Value=Double.toString(unit_atkSp_a[race][i]);
                atkSp_a.setText( atkSp_a_Value);
                dps_g_Value=Double.toString( (Math.round (     (unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i]*100)  ) *0.01     );
                dps_g.setText( dps_g_Value);
                dps_a_Value=Double.toString( (Math.round (     (unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i]*100)  ) *0.01     );
                dps_a.setText( dps_a_Value);

                if(unit_bouns_type_g[race][i]>0){
                    bouns_Value=bounsTrans(unit_bouns_type_g[race][i],unit_bouns_dmg_g[race][i],0);
                }else if((unit_bouns_type_a[race][i]>0)){
                    bouns_Value=bounsTrans(unit_bouns_type_a[race][i],unit_bouns_dmg_a[race][i],1);
                }
                else
                    bouns_Value="N/A";
                bouns.setText( bouns_Value);


                hppc.setText(Double.toString(  Math.round( (unit_hp[race][i]+unit_shield[race][i])*1.0/unit_costs[race][i]*100) *0.01 ));

                Double dps1,dps2;
                dps1=  Math.round(((unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])/unit_costs[race][i]*100) *0.01 ;
                dps2=  Math.round(((unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])/unit_costs[race][i]*100) *0.01 ;
                dpspc_g.setText(Double.toString(dps1));
                dpspc_a.setText(Double.toString(dps2));

                dps1=0.0;
                dps2=0.0;
                if(unit_bouns_type_g[race][i]>0)
                    dps1=  Math.round((( (unit_dmg_g[race][i]+unit_bouns_dmg_g[race][i])*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])*100) *0.01 ;
                else if(unit_bouns_type_a[race][i]>0)
                    dps2=  Math.round((( (unit_dmg_a[race][i]+unit_bouns_dmg_a[race][i])*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])*100) *0.01 ;

                if(dps1>dps2)
                    bdps.setText(Double.toString(dps1));
                else
                    bdps.setText(Double.toString(dps2));

                //--------------------------------------------upgrade control--------------------------------
                if(race==0){
                    upgrade2.setImageResource(R.drawable.marine_stimpack);
                    upgrade1.setImageResource(R.drawable.marauder_shells);
                    skill1.setImageResource(R.drawable.marine_stimpack);
                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.VISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==1){
                    //upgrade2.setImageResource(R.drawable.marine_stimpack);
                    upgrade1.setImageResource(R.drawable.stalker_blink);
                    skill1.setImageResource(R.drawable.stalker_blink);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==2){
                    //upgrade2.setImageResource(R.drawable.marine_stimpack);
                    // upgrade1.setImageResource(R.drawable.stalker_blink);
                    skill1.setImageResource(R.drawable.baneling_explode);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }


            }
        });

        unit_3=(ImageButton)findViewById(R.id.imageButton3);
        unit_3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                toggleFlag=false;int i=3;  if(current!=i){                     currentChange=true;                     current=i;                 }else                     currentChange=false;; updateUI();               if(race==1)                     shield.setText("("+ Integer.toString(unit_shield[race][i])  +")");                 else                     shield.setText("");
                if(race==0)
                    unit_3.setImageResource(R.drawable.reaper_select);
                else if(race==1)
                    unit_3.setImageResource(R.drawable.sentry_select);
                else if(race==2)
                    unit_3.setImageResource(R.drawable.roach_select);
                cost_Value=Integer.toString(unit_costs[race][i]);
                cost.setText(cost_Value);
                armor_Value=Integer.toString(unit_armor[race][i]);
                armor.setText(armor_Value);
                hp_Value=Integer.toString(unit_hp[race][i]);
                hp.setText(hp_Value);
                type_Value=typeTrans(unit_type[race][i]);
                type.setText(type_Value);
                range_a_Value=Integer.toString(unit_range_air[race][i]);
                range_a.setText( range_a_Value);
                range_g_Value=Integer.toString(unit_range_gnd[race][i]);
                if(unit_range_gnd[race][i]==1)                     range_g.setText( "Melee");                 else                     range_g.setText( range_g_Value);
                move_Value=Double.toString(unit_move[race][i]);
                move.setText( move_Value);
                dmg_g_Value=Integer.toString(unit_dmg_g[race][i]);
                if(unit_atks_g[race][i]>=2)
                    dmg_g_Value=dmg_g_Value+"x"+ Integer.toString(unit_atks_g[race][i]);
                dmg_g.setText( dmg_g_Value);
                dmg_a_Value=Integer.toString(unit_dmg_a[race][i]);
                if(unit_atks_a[race][i]>=2)
                    dmg_a_Value=dmg_a_Value+"x"+ Integer.toString(unit_atks_a[race][i]);
                dmg_a.setText( dmg_a_Value);
                atkSp_g_Value=Double.toString(unit_atkSp_g[race][i]);
                atkSp_g.setText( atkSp_g_Value);
                atkSp_a_Value=Double.toString(unit_atkSp_a[race][i]);
                atkSp_a.setText( atkSp_a_Value);
                dps_g_Value=Double.toString( (Math.round (     (unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i]*100)  ) *0.01     );
                dps_g.setText( dps_g_Value);
                dps_a_Value=Double.toString( (Math.round (     (unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i]*100)  ) *0.01     );
                dps_a.setText( dps_a_Value);
                if(unit_bouns_type_g[race][i]>0){
                    bouns_Value=bounsTrans(unit_bouns_type_g[race][i],unit_bouns_dmg_g[race][i],0);
                }else if((unit_bouns_type_a[race][i]>0)){
                    bouns_Value=bounsTrans(unit_bouns_type_a[race][i],unit_bouns_dmg_a[race][i],1);
                }
                else
                    bouns_Value="N/A";
                bouns.setText( bouns_Value);
                hppc.setText(Double.toString(  Math.round( (unit_hp[race][i]+unit_shield[race][i])*1.0/unit_costs[race][i]*100) *0.01 ));

                Double dps1,dps2;
                dps1=  Math.round(((unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])/unit_costs[race][i]*100) *0.01 ;
                dps2=  Math.round(((unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])/unit_costs[race][i]*100) *0.01 ;
                dpspc_g.setText(Double.toString(dps1));
                dpspc_a.setText(Double.toString(dps2));

                dps1=0.0;
                dps2=0.0;
                if(unit_bouns_type_g[race][i]>0)
                    dps1=  Math.round((( (unit_dmg_g[race][i]+unit_bouns_dmg_g[race][i])*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])*100) *0.01 ;
                else if(unit_bouns_type_a[race][i]>0)
                    dps2=  Math.round((( (unit_dmg_a[race][i]+unit_bouns_dmg_a[race][i])*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])*100) *0.01 ;

                if(dps1>dps2)
                    bdps.setText(Double.toString(dps1));
                else
                    bdps.setText(Double.toString(dps2));

//--------------------------------------------upgrade control--------------------------------
                if(race==0){
                    //upgrade1.setImageResource(R.drawable.marrine_shield);
                    //upgrade2.setImageResource(R.drawable.marine_stimpack);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.reaper_durg);
                    //skill2.setImageResource(R.drawable.);
                    //skill3.setImageResource(R.drawable.);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==1){
                    //upgrade1.setImageResource(R.drawable.marrine_shield);
                    //upgrade2.setImageResource(R.drawable.marine_stimpack);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.sentry_shield);
                    //skill2.setImageResource(R.drawable.);
                    //skill3.setImageResource(R.drawable.);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==2){
                    upgrade1.setImageResource(R.drawable.roach_tunnelling);
                    // upgrade2.setImageResource(R.drawable.marine_stimpack);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.roach_regene);
                    //skill2.setImageResource(R.drawable.);
                    //skill3.setImageResource(R.drawable.);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }

            }
        });

        unit_4=(ImageButton)findViewById(R.id.imageButton4);
        unit_4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                toggleFlag=false;int i=4;  if(current!=i){                     currentChange=true;                     current=i;                 }else                     currentChange=false;; updateUI();                if(race==1)                     shield.setText("("+ Integer.toString(unit_shield[race][i])  +")");                 else                     shield.setText("");
                if(race==0)
                    unit_4.setImageResource(R.drawable.ghost_select);
                else if(race==1)
                    unit_4.setImageResource(R.drawable.adept_select);
                else if(race==2)
                    unit_4.setImageResource(R.drawable.queens_select);
                cost_Value=Integer.toString(unit_costs[race][i]);
                cost.setText(cost_Value);
                armor_Value=Integer.toString(unit_armor[race][i]);
                armor.setText(armor_Value);
                hp_Value=Integer.toString(unit_hp[race][i]);
                hp.setText(hp_Value);
                type_Value=typeTrans(unit_type[race][i]);
                type.setText(type_Value);
                range_a_Value=Integer.toString(unit_range_air[race][i]);
                range_a.setText( range_a_Value);
                range_g_Value=Integer.toString(unit_range_gnd[race][i]);
                if(unit_range_gnd[race][i]==1)                     range_g.setText( "Melee");                 else                     range_g.setText( range_g_Value);
                move_Value=Double.toString(unit_move[race][i]);
                move.setText( move_Value);
                dmg_g_Value=Integer.toString(unit_dmg_g[race][i]);
                if(unit_atks_g[race][i]>=2)
                    dmg_g_Value=dmg_g_Value+"x"+ Integer.toString(unit_atks_g[race][i]);
                dmg_g.setText( dmg_g_Value);
                dmg_a_Value=Integer.toString(unit_dmg_a[race][i]);
                if(unit_atks_a[race][i]>=2)
                    dmg_a_Value=dmg_a_Value+"x"+ Integer.toString(unit_atks_a[race][i]);
                dmg_a.setText( dmg_a_Value);
                atkSp_g_Value=Double.toString(unit_atkSp_g[race][i]);
                atkSp_g.setText( atkSp_g_Value);
                atkSp_a_Value=Double.toString(unit_atkSp_a[race][i]);
                atkSp_a.setText( atkSp_a_Value);
                dps_g_Value=Double.toString( (Math.round (     (unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i]*100)  ) *0.01     );
                dps_g.setText( dps_g_Value);
                dps_a_Value=Double.toString( (Math.round (     (unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i]*100)  ) *0.01     );
                dps_a.setText( dps_a_Value);
                if(unit_bouns_type_g[race][i]>0){
                    if(unit_bouns_type_a[race][i]>0)
                        bouns_Value=bounsTrans(unit_bouns_type_g[race][i],unit_bouns_dmg_g[race][i],3);
                    else
                        bouns_Value=bounsTrans(unit_bouns_type_g[race][i],unit_bouns_dmg_g[race][i],0);
                }else if((unit_bouns_type_a[race][i]>0)){
                    bouns_Value=bounsTrans(unit_bouns_type_a[race][i],unit_bouns_dmg_a[race][i],1);
                }
                else
                    bouns_Value="N/A";
                bouns.setText( bouns_Value);
                hppc.setText(Double.toString(  Math.round( (unit_hp[race][i]+unit_shield[race][i])*1.0/unit_costs[race][i]*100) *0.01 ));

                Double dps1,dps2;
                dps1=  Math.round(((unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])/unit_costs[race][i]*100) *0.01 ;
                dps2=  Math.round(((unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])/unit_costs[race][i]*100) *0.01 ;
                dpspc_g.setText(Double.toString(dps1));
                dpspc_a.setText(Double.toString(dps2));

                dps1=0.0;
                dps2=0.0;
                if(unit_bouns_type_g[race][i]>0)
                    dps1=  Math.round((( (unit_dmg_g[race][i]+unit_bouns_dmg_g[race][i])*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])*100) *0.01 ;
                else if(unit_bouns_type_a[race][i]>0)
                    dps2=  Math.round((( (unit_dmg_a[race][i]+unit_bouns_dmg_a[race][i])*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])*100) *0.01 ;

                if(dps1>dps2)
                    bdps.setText(Double.toString(dps1));
                else
                    bdps.setText(Double.toString(dps2));

//--------------------------------------------upgrade control--------------------------------
                if(race==0){
                    upgrade1.setImageResource(R.drawable.ghost_cloak);
                    //upgrade2.setImageResource(R.drawable.marine_stimpack);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.ghost_snipe);
                    skill2.setImageResource(R.drawable.ghost_emp);
                    skill3.setImageResource(R.drawable.ghost_cloak);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.VISIBLE);
                    skill3.setVisibility(View.VISIBLE);
                }else if(race==1){
                    upgrade1.setImageResource(R.drawable.adept_rg);
                    //upgrade2.setImageResource(R.drawable.marine_stimpack);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.adept_transfer);
                    //skill2.setImageResource(R.drawable.ghost_emp);
                    //skill3.setImageResource(R.drawable.ghost_cloak);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==2){
                    //upgrade1.setImageResource(R.drawable.adept_rg);
                    //upgrade2.setImageResource(R.drawable.marine_stimpack);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.queens_heal);
                    //skill2.setImageResource(R.drawable.ghost_emp);
                    //skill3.setImageResource(R.drawable.ghost_cloak);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }


            }
        });

        unit_5=(ImageButton)findViewById(R.id.imageButton5);

        unit_5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if((toggleFlag==false )|| race!=1){
                    ob_oracle=true;
                    if(race==1)
                        unit_5.setImageResource(R.drawable.ob);
                    toggleFlag=true;
                    int i=5;  if(current!=i){                     currentChange=true;                     current=i;                 }else                     currentChange=false;; updateUI();                if(race==1)                     shield.setText("("+ Integer.toString(unit_shield[race][i])  +")");                 else                     shield.setText("");
                    if(race==0)
                        unit_5.setImageResource(R.drawable.hellbat_select);
                    else if(race==1)
                        unit_5.setImageResource(R.drawable.ob_select);
                    else if(race==2)
                        unit_5.setImageResource(R.drawable.overseer_select);
                    cost_Value=Integer.toString(unit_costs[race][i]);
                    cost.setText(cost_Value);
                    armor_Value=Integer.toString(unit_armor[race][i]);
                    armor.setText(armor_Value);
                    hp_Value=Integer.toString(unit_hp[race][i]);
                    hp.setText(hp_Value);
                    type_Value=typeTrans(unit_type[race][i]);
                    type.setText(type_Value);
                    range_a_Value=Integer.toString(unit_range_air[race][i]);
                    range_a.setText( range_a_Value);
                    range_g_Value=Integer.toString(unit_range_gnd[race][i]);
                    if(unit_range_gnd[race][i]==1)                     range_g.setText( "Melee");                 else                     range_g.setText( range_g_Value);
                    move_Value=Double.toString(unit_move[race][i]);
                    move.setText( move_Value);
                    dmg_g_Value=Integer.toString(unit_dmg_g[race][i]);
                    if(unit_atks_g[race][i]>=2)
                        dmg_g_Value=dmg_g_Value+"x"+ Integer.toString(unit_atks_g[race][i]);
                    dmg_g.setText( dmg_g_Value);
                    dmg_a_Value=Integer.toString(unit_dmg_a[race][i]);
                    if(unit_atks_a[race][i]>=2)
                        dmg_a_Value=dmg_a_Value+"x"+ Integer.toString(unit_atks_a[race][i]);
                    dmg_a.setText( dmg_a_Value);
                    atkSp_g_Value=Double.toString(unit_atkSp_g[race][i]);
                    atkSp_g.setText( atkSp_g_Value);
                    atkSp_a_Value=Double.toString(unit_atkSp_a[race][i]);
                    atkSp_a.setText( atkSp_a_Value);
                    dps_g_Value=Double.toString( (Math.round (     (unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i]*100)  ) *0.01     );
                    dps_g.setText( dps_g_Value);
                    dps_a_Value=Double.toString( (Math.round (     (unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i]*100)  ) *0.01     );
                    dps_a.setText( dps_a_Value);
                    if(unit_bouns_type_g[race][i]>0){
                        bouns_Value=bounsTrans(unit_bouns_type_g[race][i],unit_bouns_dmg_g[race][i],0);
                    }else if((unit_bouns_type_a[race][i]>0)){
                        bouns_Value=bounsTrans(unit_bouns_type_a[race][i],unit_bouns_dmg_a[race][i],1);
                    }
                    else
                        bouns_Value="N/A";
                    bouns.setText( bouns_Value);
                    hppc.setText(Double.toString(  Math.round( (unit_hp[race][i]+unit_shield[race][i])*1.0/unit_costs[race][i]*100) *0.01 ));

                    Double dps1,dps2;
                    dps1=  Math.round(((unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])/unit_costs[race][i]*100) *0.01 ;
                    dps2=  Math.round(((unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])/unit_costs[race][i]*100) *0.01 ;
                    dpspc_g.setText(Double.toString(dps1));
                    dpspc_a.setText(Double.toString(dps2));

                    dps1=0.0;
                    dps2=0.0;
                    if(unit_bouns_type_g[race][i]>0)
                        dps1=  Math.round((( (unit_dmg_g[race][i]+unit_bouns_dmg_g[race][i])*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])*100) *0.01 ;
                    else if(unit_bouns_type_a[race][i]>0)
                        dps2=  Math.round((( (unit_dmg_a[race][i]+unit_bouns_dmg_a[race][i])*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])*100) *0.01 ;

                    if(dps1>dps2)
                        bdps.setText(Double.toString(dps1));
                    else
                        bdps.setText(Double.toString(dps2));

//--------------------------------------------upgrade control--------------------------------
                    if(race==0){
                        hellbatOFF=false;
                        upgrade1.setImageResource(R.drawable.hellbat_bluef);
                        //upgrade2.setImageResource(R.drawable.marine_stimpack);
                        //upgrade3.setImageResource(R.drawable.);
                        skill1.setImageResource(R.drawable.hellbat_hellion);
                        //skill2.setImageResource(R.drawable.ghost_emp);
                        //skill3.setImageResource(R.drawable.ghost_cloak);

                        upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                        upgrade2.setVisibility(View.INVISIBLE);
                        upgrade3.setVisibility(View.INVISIBLE);
                        skill1.setVisibility(View.VISIBLE);
                        skill2.setVisibility(View.INVISIBLE);
                        skill3.setVisibility(View.INVISIBLE);
                    }else if(race==1){
                        upgrade1.setImageResource(R.drawable.ob_booster);
                        //upgrade2.setImageResource(R.drawable.marine_stimpack);
                        //upgrade3.setImageResource(R.drawable.);
                        skill1.setImageResource(R.drawable.ob_cloak);
                        skill2.setImageResource(R.drawable.ob_dectector);
                        //skill3.setImageResource(R.drawable.ghost_cloak);

                        upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                        upgrade2.setVisibility(View.INVISIBLE);
                        upgrade3.setVisibility(View.INVISIBLE);
                        skill1.setVisibility(View.VISIBLE);
                        skill2.setVisibility(View.VISIBLE);
                        skill3.setVisibility(View.INVISIBLE);
                    }else if(race==2){
                        upgrade1.setImageResource(R.drawable.overseer_spd);
                        //upgrade2.setImageResource(R.drawable.marine_stimpack);
                        //upgrade3.setImageResource(R.drawable.);
                        skill1.setImageResource(R.drawable.ob_dectector);
                        //skill2.setImageResource(R.drawable.ob_dectector);
                        //skill3.setImageResource(R.drawable.ghost_cloak);

                        upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                        upgrade2.setVisibility(View.INVISIBLE);
                        upgrade3.setVisibility(View.INVISIBLE);
                        skill1.setVisibility(View.VISIBLE);
                        skill2.setVisibility(View.INVISIBLE);
                        skill3.setVisibility(View.INVISIBLE);
                    }
                }else{ //orcle

                    updateUI();
                    ob_oracle=false;
                    unit_5.setImageResource(R.drawable.oracle_select);
                    toggleFlag=false;
                    int hp_=60,shield_=100,dmg_g_=15,dmg_a_=0,armor_=0,bonus=10,range=4,cost_=150;
                    double atk_sp_g=0.61,atk_sp_a=0,movement=5.6;
                    cost.setText(Integer.toString(cost_));
                    hp.setText(Integer.toString(hp_));
                    armor.setText(Integer.toBinaryString(armor_));
                    shield.setText("(" + Integer.toString(shield_)+")");
                    type.setText("Light-Mec-Psi");
                    range_a.setText(Integer.toString(range));
                    range_g.setText(Integer.toString(range));
                    move.setText(Double.toString(movement));
                    dmg_a.setText(Integer.toString(dmg_a_));
                    dmg_g.setText(Integer.toString(dmg_g_));
                    atkSp_g.setText(Double.toString(atk_sp_g));
                    atkSp_a.setText(Double.toString(atk_sp_a));
                    double tempdps=  Math.round((( (dmg_g_+0)*1.0 )/ atk_sp_g)*100) *0.01 ;
                    dps_g.setText(Double.toString(tempdps));
                    dps_a.setText(Double.toString(tempdps));
                    bouns.setText("Gnd-Lig+10");
                    hppc.setText(Double.toString((hp_+shield_*1.0)/cost_));
                    dpspc_g.setText(Double.toString(   tempdps/cost_ ));
                    dpspc_a.setText(Double.toString(   tempdps/cost_ ));
                    tempdps=  Math.round((( (dmg_g_+bonus)*1 )/ atk_sp_g)*100) *0.01 ;
                    bdps.setText(Double.toString(tempdps));

//--------------------upgrade control
                    // upgrade1.setImageResource(R.drawable.hellbat_bluef);
                    //upgrade2.setImageResource(R.drawable.marine_stimpack);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.oracle_revelation);
                    skill2.setImageResource(R.drawable.oracle_ward);
                    skill3.setImageResource(R.drawable.oracle_beam);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.VISIBLE);
                    skill3.setVisibility(View.VISIBLE);




                }



            }
        });

        unit_6=(ImageButton)findViewById(R.id.imageButton6);
        unit_6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                toggleFlag=false;int i=6;  if(current!=i){                     currentChange=true;                     current=i;                 }else                     currentChange=false;; updateUI();                if(race==1)                     shield.setText("("+ Integer.toString(unit_shield[race][i])  +")");                 else                     shield.setText("");
                if(race==0)
                    unit_6.setImageResource(R.drawable.medivac_select);
                else if(race==1)
                    unit_6.setImageResource(R.drawable.darktemplar_select);
                else if(race==2)
                    unit_6.setImageResource(R.drawable.hydralisk_select);
                cost_Value=Integer.toString(unit_costs[race][i]);
                cost.setText(cost_Value);
                armor_Value=Integer.toString(unit_armor[race][i]);
                armor.setText(armor_Value);
                hp_Value=Integer.toString(unit_hp[race][i]);
                hp.setText(hp_Value);
                type_Value=typeTrans(unit_type[race][i]);
                type.setText(type_Value);
                range_a_Value=Integer.toString(unit_range_air[race][i]);
                range_a.setText( range_a_Value);
                range_g_Value=Integer.toString(unit_range_gnd[race][i]);
                if(unit_range_gnd[race][i]==1)                     range_g.setText( "Melee");                 else                     range_g.setText( range_g_Value);
                move_Value=Double.toString(unit_move[race][i]);
                move.setText( move_Value);
                dmg_g_Value=Integer.toString(unit_dmg_g[race][i]);
                if(unit_atks_g[race][i]>=2)
                    dmg_g_Value=dmg_g_Value+"x"+ Integer.toString(unit_atks_g[race][i]);
                dmg_g.setText( dmg_g_Value);
                dmg_a_Value=Integer.toString(unit_dmg_a[race][i]);
                if(unit_atks_a[race][i]>=2)
                    dmg_a_Value=dmg_a_Value+"x"+ Integer.toString(unit_atks_a[race][i]);
                dmg_a.setText( dmg_a_Value);
                atkSp_g_Value=Double.toString(unit_atkSp_g[race][i]);
                atkSp_g.setText( atkSp_g_Value);
                atkSp_a_Value=Double.toString(unit_atkSp_a[race][i]);
                atkSp_a.setText( atkSp_a_Value);
                dps_g_Value=Double.toString( (Math.round (     (unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i]*100)  ) *0.01     );
                dps_g.setText( dps_g_Value);
                dps_a_Value=Double.toString( (Math.round (     (unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i]*100)  ) *0.01     );
                dps_a.setText( dps_a_Value);
                if(unit_bouns_type_g[race][i]>0){
                    bouns_Value=bounsTrans(unit_bouns_type_g[race][i],unit_bouns_dmg_g[race][i],0);
                }else if((unit_bouns_type_a[race][i]>0)){
                    bouns_Value=bounsTrans(unit_bouns_type_a[race][i],unit_bouns_dmg_a[race][i],1);
                }
                else
                    bouns_Value="N/A";
                bouns.setText( bouns_Value);
                hppc.setText(Double.toString(  Math.round( (unit_hp[race][i]+unit_shield[race][i])*1.0/unit_costs[race][i]*100) *0.01 ));

                Double dps1,dps2;
                dps1=  Math.round(((unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])/unit_costs[race][i]*100) *0.01 ;
                dps2=  Math.round(((unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])/unit_costs[race][i]*100) *0.01 ;
                dpspc_g.setText(Double.toString(dps1));
                dpspc_a.setText(Double.toString(dps2));

                dps1=0.0;
                dps2=0.0;
                if(unit_bouns_type_g[race][i]>0)
                    dps1=  Math.round((( (unit_dmg_g[race][i]+unit_bouns_dmg_g[race][i])*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])*100) *0.01 ;
                else if(unit_bouns_type_a[race][i]>0)
                    dps2=  Math.round((( (unit_dmg_a[race][i]+unit_bouns_dmg_a[race][i])*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])*100) *0.01 ;

                if(dps1>dps2)
                    bdps.setText(Double.toString(dps1));
                else
                    bdps.setText(Double.toString(dps2));

                //--------------------------------------------upgrade control--------------------------------
                if(race==0){
                    //upgrade1.setImageResource(R.drawable.hellbat_bluef);
                    //upgrade2.setImageResource(R.drawable.marine_stimpack);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.medivac_heal);
                    skill2.setImageResource(R.drawable.medivac_afterburner);
                    //skill3.setImageResource(R.drawable.ghost_cloak);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.VISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==1){
                    upgrade1.setImageResource(R.drawable.stalker_blink);
                    upgrade2.setImageResource(R.drawable.archon);
                    //upgrade3.setImageResource(R.drawable.);
                    // skill1.setImageResource(R.drawable.medivac_heal);
                    // skill2.setImageResource(R.drawable.medivac_afterburner);
                    //skill3.setImageResource(R.drawable.ghost_cloak);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.VISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.INVISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==2){
                    upgrade1.setImageResource(R.drawable.hydralisk_musclular);
                    //upgrade2.setImageResource(R.drawable.archon);
                    //upgrade3.setImageResource(R.drawable.);
                    // skill1.setImageResource(R.drawable.medivac_heal);
                    // skill2.setImageResource(R.drawable.medivac_afterburner);
                    //skill3.setImageResource(R.drawable.ghost_cloak);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.INVISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }



            }
        });

        unit_7=(ImageButton)findViewById(R.id.imageButton7);
        unit_7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                toggleFlag=false;int i=7;  if(current!=i){                     currentChange=true;                     current=i;                 }else                     currentChange=false;; updateUI();                if(race==1)                     shield.setText("("+ Integer.toString(unit_shield[race][i])  +")");                 else                     shield.setText("");
                if(race==0)
                    unit_7.setImageResource(R.drawable.banshee_select);
                else if(race==1)
                    unit_7.setImageResource(R.drawable.disruptor_select);
                else if(race==2)
                    unit_7.setImageResource(R.drawable.mutalisk_select);
                cost_Value=Integer.toString(unit_costs[race][i]);
                cost.setText(cost_Value);
                armor_Value=Integer.toString(unit_armor[race][i]);
                armor.setText(armor_Value);
                hp_Value=Integer.toString(unit_hp[race][i]);
                hp.setText(hp_Value);
                type_Value=typeTrans(unit_type[race][i]);
                type.setText(type_Value);
                range_a_Value=Integer.toString(unit_range_air[race][i]);
                range_a.setText( range_a_Value);
                range_g_Value=Integer.toString(unit_range_gnd[race][i]);
                if(unit_range_gnd[race][i]==1)                     range_g.setText( "Melee");                 else                     range_g.setText( range_g_Value);
                move_Value=Double.toString(unit_move[race][i]);
                move.setText( move_Value);
                dmg_g_Value=Integer.toString(unit_dmg_g[race][i]);
                if(unit_atks_g[race][i]>=2)
                    dmg_g_Value=dmg_g_Value+"x"+ Integer.toString(unit_atks_g[race][i]);
                dmg_g.setText( dmg_g_Value);
                dmg_a_Value=Integer.toString(unit_dmg_a[race][i]);
                if(unit_atks_a[race][i]>=2)
                    dmg_a_Value=dmg_a_Value+"x"+ Integer.toString(unit_atks_a[race][i]);
                dmg_a.setText( dmg_a_Value);
                atkSp_g_Value=Double.toString(unit_atkSp_g[race][i]);
                atkSp_g.setText( atkSp_g_Value);
                atkSp_a_Value=Double.toString(unit_atkSp_a[race][i]);
                atkSp_a.setText( atkSp_a_Value);
                dps_g_Value=Double.toString( (Math.round (     (unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i]*100)  ) *0.01     );
                dps_g.setText( dps_g_Value);
                dps_a_Value=Double.toString( (Math.round (     (unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i]*100)  ) *0.01     );
                dps_a.setText( dps_a_Value);
                if(unit_bouns_type_g[race][i]>0){
                    bouns_Value=bounsTrans(unit_bouns_type_g[race][i],unit_bouns_dmg_g[race][i],0);
                }else if((unit_bouns_type_a[race][i]>0)){
                    bouns_Value=bounsTrans(unit_bouns_type_a[race][i],unit_bouns_dmg_a[race][i],1);
                }
                else
                    bouns_Value="N/A";
                bouns.setText( bouns_Value);
                hppc.setText(Double.toString(  Math.round( (unit_hp[race][i]+unit_shield[race][i])*1.0/unit_costs[race][i]*100) *0.01 ));

                Double dps1,dps2;
                dps1=  Math.round(((unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])/unit_costs[race][i]*100) *0.01 ;
                dps2=  Math.round(((unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])/unit_costs[race][i]*100) *0.01 ;
                dpspc_g.setText(Double.toString(dps1));
                dpspc_a.setText(Double.toString(dps2));

                dps1=0.0;
                dps2=0.0;
                if(unit_bouns_type_g[race][i]>0)
                    dps1=  Math.round((( (unit_dmg_g[race][i]+unit_bouns_dmg_g[race][i])*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])*100) *0.01 ;
                else if(unit_bouns_type_a[race][i]>0)
                    dps2=  Math.round((( (unit_dmg_a[race][i]+unit_bouns_dmg_a[race][i])*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])*100) *0.01 ;

                if(dps1>dps2)
                    bdps.setText(Double.toString(dps1));
                else
                    bdps.setText(Double.toString(dps2));

//--------------------------------------------upgrade control--------------------------------
                if(race==0){
                    //upgrade1.setImageResource(R.drawable.hellbat_bluef);
                    //upgrade2.setImageResource(R.drawable.marine_stimpack);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.ghost_cloak);
                    //skill2.setImageResource(R.drawable.medivac_afterburner);
                    //skill3.setImageResource(R.drawable.ghost_cloak);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==1){
                    //upgrade1.setImageResource(R.drawable.hellbat_bluef);
                    //upgrade2.setImageResource(R.drawable.marine_stimpack);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.disruptor_nova);
                    //skill2.setImageResource(R.drawable.medivac_afterburner);
                    //skill3.setImageResource(R.drawable.ghost_cloak);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==2){
                    //upgrade1.setImageResource(R.drawable.hellbat_bluef);
                    //upgrade2.setImageResource(R.drawable.marine_stimpack);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.mutalisk_regen);
                    //skill2.setImageResource(R.drawable.medivac_afterburner);
                    //skill3.setImageResource(R.drawable.ghost_cloak);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }

            }
        });

        unit_8=(ImageButton)findViewById(R.id.imageButton8);
        unit_8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                toggleFlag=false;int i=8;  if(current!=i){                     currentChange=true;                     current=i;                 }else                     currentChange=false;; updateUI();                if(race==1)                     shield.setText("("+ Integer.toString(unit_shield[race][i])  +")");                 else                     shield.setText("");
                if(race==0)
                    unit_8.setImageResource(R.drawable.viking_select);
                else if(race==1)
                    unit_8.setImageResource(R.drawable.phoenix_select);
                else if(race==2)
                    unit_8.setImageResource(R.drawable.corruptor_select);
                cost_Value=Integer.toString(unit_costs[race][i]);
                cost.setText(cost_Value);
                armor_Value=Integer.toString(unit_armor[race][i]);
                armor.setText(armor_Value);
                hp_Value=Integer.toString(unit_hp[race][i]);
                hp.setText(hp_Value);
                type_Value=typeTrans(unit_type[race][i]);
                type.setText(type_Value);
                range_a_Value=Integer.toString(unit_range_air[race][i]);
                range_a.setText( range_a_Value);
                range_g_Value=Integer.toString(unit_range_gnd[race][i]);
                if(unit_range_gnd[race][i]==1)                     range_g.setText( "Melee");                 else                     range_g.setText( range_g_Value);
                move_Value=Double.toString(unit_move[race][i]);
                move.setText( move_Value);
                dmg_g_Value=Integer.toString(unit_dmg_g[race][i]);
                if(unit_atks_g[race][i]>=2)
                    dmg_g_Value=dmg_g_Value+"x"+ Integer.toString(unit_atks_g[race][i]);
                dmg_g.setText( dmg_g_Value);
                dmg_a_Value=Integer.toString(unit_dmg_a[race][i]);
                if(unit_atks_a[race][i]>=2)
                    dmg_a_Value=dmg_a_Value+"x"+ Integer.toString(unit_atks_a[race][i]);
                dmg_a.setText( dmg_a_Value);
                atkSp_g_Value=Double.toString(unit_atkSp_g[race][i]);
                atkSp_g.setText( atkSp_g_Value);
                atkSp_a_Value=Double.toString(unit_atkSp_a[race][i]);
                atkSp_a.setText( atkSp_a_Value);
                dps_g_Value=Double.toString( (Math.round (     (unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i]*100)  ) *0.01     );
                dps_g.setText( dps_g_Value);
                dps_a_Value=Double.toString( (Math.round (     (unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i]*100)  ) *0.01     );
                dps_a.setText( dps_a_Value);
                if(unit_bouns_type_g[race][i]>0){
                    bouns_Value=bounsTrans(unit_bouns_type_g[race][i],unit_bouns_dmg_g[race][i],0);
                }else if((unit_bouns_type_a[race][i]>0)){
                    bouns_Value=bounsTrans(unit_bouns_type_a[race][i],unit_bouns_dmg_a[race][i],1);
                }
                else
                    bouns_Value="N/A";
                bouns.setText( bouns_Value);
                hppc.setText(Double.toString(  Math.round( (unit_hp[race][i]+unit_shield[race][i])*1.0/unit_costs[race][i]*100) *0.01 ));

                Double dps1,dps2;
                dps1=  Math.round(((unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])/unit_costs[race][i]*100) *0.01 ;
                dps2=  Math.round(((unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])/unit_costs[race][i]*100) *0.01 ;
                dpspc_g.setText(Double.toString(dps1));
                dpspc_a.setText(Double.toString(dps2));

                dps1=0.0;
                dps2=0.0;
                if(unit_bouns_type_g[race][i]>0)
                    dps1=  Math.round((( (unit_dmg_g[race][i]+unit_bouns_dmg_g[race][i])*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])*100) *0.01 ;
                else if(unit_bouns_type_a[race][i]>0)
                    dps2=  Math.round((( (unit_dmg_a[race][i]+unit_bouns_dmg_a[race][i])*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])*100) *0.01 ;

                if(dps1>dps2)
                    bdps.setText(Double.toString(dps1));
                else
                    bdps.setText(Double.toString(dps2));

//--------------------------------------------upgrade control--------------------------------
                if(race==0){
                    //upgrade1.setImageResource(R.drawable.hellbat_bluef);
                    //upgrade2.setImageResource(R.drawable.marine_stimpack);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.viking_assault);
                    //skill2.setImageResource(R.drawable.medivac_afterburner);
                    //skill3.setImageResource(R.drawable.ghost_cloak);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==1){
                    upgrade1.setImageResource(R.drawable.phoenix_pulse);
                    //upgrade2.setImageResource(R.drawable.marine_stimpack);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.phoenix_grav);
                    //skill2.setImageResource(R.drawable.medivac_afterburner);
                    //skill3.setImageResource(R.drawable.ghost_cloak);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==2){
                    //upgrade1.setImageResource(R.drawable.hellbat_bluef);
                    //upgrade2.setImageResource(R.drawable.marine_stimpack);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.corruptor_spray);
                    //skill2.setImageResource(R.drawable.medivac_afterburner);
                    //skill3.setImageResource(R.drawable.ghost_cloak);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }



            }
        });

        unit_9=(ImageButton)findViewById(R.id.imageButton9);
        unit_9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                toggleFlag=false;int i=9;  if(current!=i){                     currentChange=true;                     current=i;                 }else                     currentChange=false;; updateUI();                if(race==1)                     shield.setText("("+ Integer.toString(unit_shield[race][i])  +")");                 else                     shield.setText("");
                if(race==0)
                    unit_9.setImageResource(R.drawable.raven_select);
                else if(race==1)
                    unit_9.setImageResource(R.drawable.high_templer_select);
                else if(race==2)
                    unit_9.setImageResource(R.drawable.infestor_select);
                cost_Value=Integer.toString(unit_costs[race][i]);
                cost.setText(cost_Value);
                armor_Value=Integer.toString(unit_armor[race][i]);
                armor.setText(armor_Value);
                hp_Value=Integer.toString(unit_hp[race][i]);
                hp.setText(hp_Value);
                type_Value=typeTrans(unit_type[race][i]);
                type.setText(type_Value);
                range_a_Value=Integer.toString(unit_range_air[race][i]);
                range_a.setText( range_a_Value);
                range_g_Value=Integer.toString(unit_range_gnd[race][i]);
                if(unit_range_gnd[race][i]==1)                     range_g.setText( "Melee");                 else                     range_g.setText( range_g_Value);
                move_Value=Double.toString(unit_move[race][i]);
                move.setText( move_Value);
                dmg_g_Value=Integer.toString(unit_dmg_g[race][i]);
                if(unit_atks_g[race][i]>=2)
                    dmg_g_Value=dmg_g_Value+"x"+ Integer.toString(unit_atks_g[race][i]);
                dmg_g.setText( dmg_g_Value);
                dmg_a_Value=Integer.toString(unit_dmg_a[race][i]);
                if(unit_atks_a[race][i]>=2)
                    dmg_a_Value=dmg_a_Value+"x"+ Integer.toString(unit_atks_a[race][i]);
                dmg_a.setText( dmg_a_Value);
                atkSp_g_Value=Double.toString(unit_atkSp_g[race][i]);
                atkSp_g.setText( atkSp_g_Value);
                atkSp_a_Value=Double.toString(unit_atkSp_a[race][i]);
                atkSp_a.setText( atkSp_a_Value);
                dps_g_Value=Double.toString( (Math.round (     (unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i]*100)  ) *0.01     );
                dps_g.setText( dps_g_Value);
                dps_a_Value=Double.toString( (Math.round (     (unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i]*100)  ) *0.01     );
                dps_a.setText( dps_a_Value);
                if(unit_bouns_type_g[race][i]>0){
                    bouns_Value=bounsTrans(unit_bouns_type_g[race][i],unit_bouns_dmg_g[race][i],0);
                }else if((unit_bouns_type_a[race][i]>0)){
                    bouns_Value=bounsTrans(unit_bouns_type_a[race][i],unit_bouns_dmg_a[race][i],1);
                }
                else
                    bouns_Value="N/A";
                bouns.setText( bouns_Value);
                hppc.setText(Double.toString(  Math.round( (unit_hp[race][i]+unit_shield[race][i])*1.0/unit_costs[race][i]*100) *0.01 ));

                Double dps1,dps2;
                dps1=  Math.round(((unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])/unit_costs[race][i]*100) *0.01 ;
                dps2=  Math.round(((unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])/unit_costs[race][i]*100) *0.01 ;
                dpspc_g.setText(Double.toString(dps1));
                dpspc_a.setText(Double.toString(dps2));

                dps1=0.0;
                dps2=0.0;
                if(unit_bouns_type_g[race][i]>0)
                    dps1=  Math.round((( (unit_dmg_g[race][i]+unit_bouns_dmg_g[race][i])*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])*100) *0.01 ;
                else if(unit_bouns_type_a[race][i]>0)
                    dps2=  Math.round((( (unit_dmg_a[race][i]+unit_bouns_dmg_a[race][i])*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])*100) *0.01 ;

                if(dps1>dps2)
                    bdps.setText(Double.toString(dps1));
                else
                    bdps.setText(Double.toString(dps2));

                //--------------------------------------------upgrade control--------------------------------
                if(race==0){
                    turret=false;
                    upgrade1.setImageResource(R.drawable.raven_reactor);
                    upgrade2.setImageResource(R.drawable.raven_shells);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.raven_turret);
                    skill2.setImageResource(R.drawable.raven_drone);
                    skill3.setImageResource(R.drawable.raven_missile);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.VISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.VISIBLE);
                    skill3.setVisibility(View.VISIBLE);
                }else if(race==1){

                    upgrade1.setImageResource(R.drawable.high_templer_storm);
                    upgrade2.setImageResource(R.drawable.archon);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.high_templer_feedback);
                    skill2.setImageResource(R.drawable.high_templer_storm);
                    //skill3.setImageResource(R.drawable.raven_missile);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.VISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.VISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==2){
                    upgrade1.setImageResource(R.drawable.infestor_en);
                    upgrade2.setImageResource(R.drawable.infestor_neural);
                    //upgrade3.setImageResource(R.drawable.);
                    skill2.setImageResource(R.drawable.infestor_fungal);
                    skill1.setImageResource(R.drawable.infestor_terran);
                    skill3.setImageResource(R.drawable.infestor_neural);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.VISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.VISIBLE);
                    skill3.setVisibility(View.VISIBLE);
                }


            }
        });

        unit_10=(ImageButton)findViewById(R.id.imageButton10);
        unit_10.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                toggleFlag=false;int i=10;  if(current!=i){                     currentChange=true;                     current=i;                 }else                     currentChange=false;; updateUI();                if(race==1)                     shield.setText("("+ Integer.toString(unit_shield[race][i])  +")");                 else                     shield.setText("");
                if(race==0)
                    unit_10.setImageResource(R.drawable.tank_select);
                else if(race==1)
                    unit_10.setImageResource(R.drawable.immortal_select);
                else if(race==2)
                    unit_10.setImageResource(R.drawable.host_select);
                cost_Value=Integer.toString(unit_costs[race][i]);
                cost.setText(cost_Value);
                armor_Value=Integer.toString(unit_armor[race][i]);
                armor.setText(armor_Value);
                hp_Value=Integer.toString(unit_hp[race][i]);
                hp.setText(hp_Value);
                type_Value=typeTrans(unit_type[race][i]);
                type.setText(type_Value);
                range_a_Value=Integer.toString(unit_range_air[race][i]);
                range_a.setText( range_a_Value);
                range_g_Value=Integer.toString(unit_range_gnd[race][i]);
                if(unit_range_gnd[race][i]==1)                     range_g.setText( "Melee");                 else                     range_g.setText( range_g_Value);
                move_Value=Double.toString(unit_move[race][i]);
                move.setText( move_Value);
                dmg_g_Value=Integer.toString(unit_dmg_g[race][i]);
                if(unit_atks_g[race][i]>=2)
                    dmg_g_Value=dmg_g_Value+"x"+ Integer.toString(unit_atks_g[race][i]);
                dmg_g.setText( dmg_g_Value);
                dmg_a_Value=Integer.toString(unit_dmg_a[race][i]);
                if(unit_atks_a[race][i]>=2)
                    dmg_a_Value=dmg_a_Value+"x"+ Integer.toString(unit_atks_a[race][i]);
                dmg_a.setText( dmg_a_Value);
                atkSp_g_Value=Double.toString(unit_atkSp_g[race][i]);
                atkSp_g.setText( atkSp_g_Value);
                atkSp_a_Value=Double.toString(unit_atkSp_a[race][i]);
                atkSp_a.setText( atkSp_a_Value);
                dps_g_Value=Double.toString( (Math.round (     (unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i]*100)  ) *0.01     );
                dps_g.setText( dps_g_Value);
                dps_a_Value=Double.toString( (Math.round (     (unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i]*100)  ) *0.01     );
                dps_a.setText( dps_a_Value);
                if(unit_bouns_type_g[race][i]>0){
                    bouns_Value=bounsTrans(unit_bouns_type_g[race][i],unit_bouns_dmg_g[race][i],0);
                }else if((unit_bouns_type_a[race][i]>0)){
                    bouns_Value=bounsTrans(unit_bouns_type_a[race][i],unit_bouns_dmg_a[race][i],1);
                }
                else
                    bouns_Value="N/A";
                bouns.setText( bouns_Value);
                hppc.setText(Double.toString(  Math.round( (unit_hp[race][i]+unit_shield[race][i])*1.0/unit_costs[race][i]*100) *0.01 ));

                Double dps1,dps2;
                dps1=  Math.round(((unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])/unit_costs[race][i]*100) *0.01 ;
                dps2=  Math.round(((unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])/unit_costs[race][i]*100) *0.01 ;
                dpspc_g.setText(Double.toString(dps1));
                dpspc_a.setText(Double.toString(dps2));

                dps1=0.0;
                dps2=0.0;
                if(unit_bouns_type_g[race][i]>0)
                    dps1=  Math.round((( (unit_dmg_g[race][i]+unit_bouns_dmg_g[race][i])*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])*100) *0.01 ;
                else if(unit_bouns_type_a[race][i]>0)
                    dps2=  Math.round((( (unit_dmg_a[race][i]+unit_bouns_dmg_a[race][i])*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])*100) *0.01 ;

                if(dps1>dps2)
                    bdps.setText(Double.toString(dps1));
                else
                    bdps.setText(Double.toString(dps2));


                //--------------------------------------------upgrade control--------------------------------
                if(race==0){
                    //upgrade1.setImageResource(R.drawable.raven_reactor);
                    // upgrade2.setImageResource(R.drawable.raven_shells);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.tank_siege);
                    //skill2.setImageResource(R.drawable.raven_drone);
                    // skill3.setImageResource(R.drawable.raven_missile);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==1){
                    //upgrade1.setImageResource(R.drawable.raven_reactor);
                    // upgrade2.setImageResource(R.drawable.raven_shells);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.immortal_barrier);
                    //skill2.setImageResource(R.drawable.raven_drone);
                    // skill3.setImageResource(R.drawable.raven_missile);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==2){
                    //upgrade1.setImageResource(R.drawable.raven_reactor);
                    // upgrade2.setImageResource(R.drawable.raven_shells);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.host_spawn);
                    //skill2.setImageResource(R.drawable.raven_drone);
                    // skill3.setImageResource(R.drawable.raven_missile);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }



            }
        });

        unit_11=(ImageButton)findViewById(R.id.imageButton11);
        unit_11.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                toggleFlag=false;int i=11;  if(current!=i){                     currentChange=true;                     current=i;                 }else                     currentChange=false;; updateUI();                if(race==1)                     shield.setText("("+ Integer.toString(unit_shield[race][i])  +")");                 else                     shield.setText("");
                if(race==0)
                    unit_11.setImageResource(R.drawable.mine_select);
                else if(race==1)
                    unit_11.setImageResource(R.drawable.void_select);
                else if(race==2)
                    unit_11.setImageResource(R.drawable.lurker_select);
                cost_Value=Integer.toString(unit_costs[race][i]);
                cost.setText(cost_Value);
                armor_Value=Integer.toString(unit_armor[race][i]);
                armor.setText(armor_Value);
                hp_Value=Integer.toString(unit_hp[race][i]);
                hp.setText(hp_Value);
                type_Value=typeTrans(unit_type[race][i]);
                type.setText(type_Value);
                range_a_Value=Integer.toString(unit_range_air[race][i]);
                range_a.setText( range_a_Value);
                range_g_Value=Integer.toString(unit_range_gnd[race][i]);
                if(unit_range_gnd[race][i]==1)                     range_g.setText( "Melee");                 else                     range_g.setText( range_g_Value);
                move_Value=Double.toString(unit_move[race][i]);
                move.setText( move_Value);
                dmg_g_Value=Integer.toString(unit_dmg_g[race][i]);
                if(unit_atks_g[race][i]>=2)
                    dmg_g_Value=dmg_g_Value+"x"+ Integer.toString(unit_atks_g[race][i]);
                dmg_g.setText( dmg_g_Value);
                dmg_a_Value=Integer.toString(unit_dmg_a[race][i]);
                if(unit_atks_a[race][i]>=2)
                    dmg_a_Value=dmg_a_Value+"x"+ Integer.toString(unit_atks_a[race][i]);
                dmg_a.setText( dmg_a_Value);
                atkSp_g_Value=Double.toString(unit_atkSp_g[race][i]);
                atkSp_g.setText( atkSp_g_Value);
                atkSp_a_Value=Double.toString(unit_atkSp_a[race][i]);
                atkSp_a.setText( atkSp_a_Value);
                dps_g_Value=Double.toString( (Math.round (     (unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i]*100)  ) *0.01     );
                dps_g.setText( dps_g_Value);
                dps_a_Value=Double.toString( (Math.round (     (unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i]*100)  ) *0.01     );
                dps_a.setText( dps_a_Value);
                if(unit_bouns_type_g[race][i]>0){
                    bouns_Value=bounsTrans(unit_bouns_type_g[race][i],unit_bouns_dmg_g[race][i],0);
                }else if((unit_bouns_type_a[race][i]>0)){
                    bouns_Value=bounsTrans(unit_bouns_type_a[race][i],unit_bouns_dmg_a[race][i],1);
                }
                else
                    bouns_Value="N/A";
                bouns.setText( bouns_Value);
                hppc.setText(Double.toString(  Math.round( (unit_hp[race][i]+unit_shield[race][i])*1.0/unit_costs[race][i]*100) *0.01 ));

                Double dps1,dps2;
                dps1=  Math.round(((unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])/unit_costs[race][i]*100) *0.01 ;
                dps2=  Math.round(((unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])/unit_costs[race][i]*100) *0.01 ;
                dpspc_g.setText(Double.toString(dps1));
                dpspc_a.setText(Double.toString(dps2));

                dps1=0.0;
                dps2=0.0;
                if(unit_bouns_type_g[race][i]>0)
                    dps1=  Math.round((( (unit_dmg_g[race][i]+unit_bouns_dmg_g[race][i])*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])*100) *0.01 ;
                else if(unit_bouns_type_a[race][i]>0)
                    dps2=  Math.round((( (unit_dmg_a[race][i]+unit_bouns_dmg_a[race][i])*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])*100) *0.01 ;

                if(dps1>dps2)
                    bdps.setText(Double.toString(dps1));
                else
                    bdps.setText(Double.toString(dps2));

//--------------------------------------------upgrade control--------------------------------
                if(race==0){
                    //upgrade1.setImageResource(R.drawable.raven_reactor);
                    // upgrade2.setImageResource(R.drawable.raven_shells);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.mine_active);
                    skill2.setImageResource(R.drawable.mine_missile);
                    // skill3.setImageResource(R.drawable.raven_missile);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.VISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==1){
                    //upgrade1.setImageResource(R.drawable.raven_reactor);
                    // upgrade2.setImageResource(R.drawable.raven_shells);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.voidray_charge);
                    //skill2.setImageResource(R.drawable.mine_missile);
                    // skill3.setImageResource(R.drawable.raven_missile);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==2){
                    //upgrade1.setImageResource(R.drawable.raven_reactor);
                    // upgrade2.setImageResource(R.drawable.raven_shells);
                    //upgrade3.setImageResource(R.drawable.);
                    // skill1.setImageResource(R.drawable.);
                    //skill2.setImageResource(R.drawable.raven_drone);
                    // skill3.setImageResource(R.drawable.raven_missile);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.INVISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }



            }
        });

        unit_12=(ImageButton)findViewById(R.id.imageButton12);
        unit_12.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                toggleFlag=false;int i=12;  if(current!=i){                     currentChange=true;                     current=i;                 }else                     currentChange=false;; updateUI();                if(race==1)                     shield.setText("("+ Integer.toString(unit_shield[race][i])  +")");                 else                     shield.setText("");
                if(race==0)
                    unit_12.setImageResource(R.drawable.cyclone_select);
                else if(race==1)
                    unit_12.setImageResource(R.drawable.colossus_select);
                else if(race==2)
                    unit_12.setImageResource(R.drawable.ravager_select);
                cost_Value=Integer.toString(unit_costs[race][i]);
                cost.setText(cost_Value);
                armor_Value=Integer.toString(unit_armor[race][i]);
                armor.setText(armor_Value);
                hp_Value=Integer.toString(unit_hp[race][i]);
                hp.setText(hp_Value);
                type_Value=typeTrans(unit_type[race][i]);
                type.setText(type_Value);
                range_a_Value=Integer.toString(unit_range_air[race][i]);
                range_a.setText( range_a_Value);
                range_g_Value=Integer.toString(unit_range_gnd[race][i]);
                if(unit_range_gnd[race][i]==1)                     range_g.setText( "Melee");                 else                     range_g.setText( range_g_Value);
                move_Value=Double.toString(unit_move[race][i]);
                move.setText( move_Value);
                dmg_g_Value=Integer.toString(unit_dmg_g[race][i]);
                if(unit_atks_g[race][i]>=2)
                    dmg_g_Value=dmg_g_Value+"x"+ Integer.toString(unit_atks_g[race][i]);
                dmg_g.setText( dmg_g_Value);
                dmg_a_Value=Integer.toString(unit_dmg_a[race][i]);
                if(unit_atks_a[race][i]>=2)
                    dmg_a_Value=dmg_a_Value+"x"+ Integer.toString(unit_atks_a[race][i]);
                dmg_a.setText( dmg_a_Value);
                atkSp_g_Value=Double.toString(unit_atkSp_g[race][i]);
                atkSp_g.setText( atkSp_g_Value);
                atkSp_a_Value=Double.toString(unit_atkSp_a[race][i]);
                atkSp_a.setText( atkSp_a_Value);
                dps_g_Value=Double.toString( (Math.round (     (unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i]*100)  ) *0.01     );
                dps_g.setText( dps_g_Value);
                dps_a_Value=Double.toString( (Math.round (     (unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i]*100)  ) *0.01     );
                dps_a.setText( dps_a_Value);
                if(unit_bouns_type_g[race][i]>0){
                    bouns_Value=bounsTrans(unit_bouns_type_g[race][i],unit_bouns_dmg_g[race][i],0);
                }else if((unit_bouns_type_a[race][i]>0)){
                    bouns_Value=bounsTrans(unit_bouns_type_a[race][i],unit_bouns_dmg_a[race][i],1);
                }
                else
                    bouns_Value="N/A";
                bouns.setText( bouns_Value);
                hppc.setText(Double.toString(  Math.round( (unit_hp[race][i]+unit_shield[race][i])*1.0/unit_costs[race][i]*100) *0.01 ));

                Double dps1,dps2;
                dps1=  Math.round(((unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])/unit_costs[race][i]*100) *0.01 ;
                dps2=  Math.round(((unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])/unit_costs[race][i]*100) *0.01 ;
                dpspc_g.setText(Double.toString(dps1));
                dpspc_a.setText(Double.toString(dps2));

                dps1=0.0;
                dps2=0.0;
                if(unit_bouns_type_g[race][i]>0)
                    dps1=  Math.round((( (unit_dmg_g[race][i]+unit_bouns_dmg_g[race][i])*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])*100) *0.01 ;
                else if(unit_bouns_type_a[race][i]>0)
                    dps2=  Math.round((( (unit_dmg_a[race][i]+unit_bouns_dmg_a[race][i])*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])*100) *0.01 ;

                if(dps1>dps2)
                    bdps.setText(Double.toString(dps1));
                else
                    bdps.setText(Double.toString(dps2));

//--------------------------------------------upgrade control--------------------------------
                if(race==0){
                    //upgrade1.setImageResource(R.drawable.raven_reactor);
                    // upgrade2.setImageResource(R.drawable.raven_shells);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.cyclone_lockon);
                    // skill2.setImageResource(R.drawable.mine_missile);
                    // skill3.setImageResource(R.drawable.raven_missile);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);

                }else if(race==1){
                    upgrade1.setImageResource(R.drawable.colossus_lance);
                    // upgrade2.setImageResource(R.drawable.raven_shells);
                    //upgrade3.setImageResource(R.drawable.);
                    //skill1.setImageResource(R.drawable.cyclone_lockon);
                    // skill2.setImageResource(R.drawable.mine_missile);
                    // skill3.setImageResource(R.drawable.raven_missile);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.INVISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==2){
                    //upgrade1.setImageResource(R.drawable.raven_reactor);
                    // upgrade2.setImageResource(R.drawable.raven_shells);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.ravager_bile);
                    //skill2.setImageResource(R.drawable.raven_drone);
                    // skill3.setImageResource(R.drawable.raven_missile);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }

            }
        });

        unit_13=(ImageButton)findViewById(R.id.imageButton13);
        unit_13.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                toggleFlag=false;int i=13;  if(current!=i){                     currentChange=true;                     current=i;                 }else                     currentChange=false;; updateUI();                if(race==1)                     shield.setText("("+ Integer.toString(unit_shield[race][i])  +")");                 else                     shield.setText("");
                if(race==0)
                    unit_13.setImageResource(R.drawable.liberator_select);
                else if(race==1)
                    unit_13.setImageResource(R.drawable.tempest_select);
                else if(race==2)
                    unit_13.setImageResource(R.drawable.viper_select);
                cost_Value=Integer.toString(unit_costs[race][i]);
                cost.setText(cost_Value);
                armor_Value=Integer.toString(unit_armor[race][i]);
                armor.setText(armor_Value);
                hp_Value=Integer.toString(unit_hp[race][i]);
                hp.setText(hp_Value);
                type_Value=typeTrans(unit_type[race][i]);
                type.setText(type_Value);
                range_a_Value=Integer.toString(unit_range_air[race][i]);
                range_a.setText( range_a_Value);
                range_g_Value=Integer.toString(unit_range_gnd[race][i]);
                if(unit_range_gnd[race][i]==1)                     range_g.setText( "Melee");                 else                     range_g.setText( range_g_Value);
                move_Value=Double.toString(unit_move[race][i]);
                move.setText( move_Value);
                dmg_g_Value=Integer.toString(unit_dmg_g[race][i]);
                if(unit_atks_g[race][i]>=2)
                    dmg_g_Value=dmg_g_Value+"x"+ Integer.toString(unit_atks_g[race][i]);
                dmg_g.setText( dmg_g_Value);
                dmg_a_Value=Integer.toString(unit_dmg_a[race][i]);
                if(unit_atks_a[race][i]>=2)
                    dmg_a_Value=dmg_a_Value+"x"+ Integer.toString(unit_atks_a[race][i]);
                dmg_a.setText( dmg_a_Value);
                atkSp_g_Value=Double.toString(unit_atkSp_g[race][i]);
                atkSp_g.setText( atkSp_g_Value);
                atkSp_a_Value=Double.toString(unit_atkSp_a[race][i]);
                atkSp_a.setText( atkSp_a_Value);
                dps_g_Value=Double.toString( (Math.round (     (unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i]*100)  ) *0.01     );
                dps_g.setText( dps_g_Value);
                dps_a_Value=Double.toString( (Math.round (     (unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i]*100)  ) *0.01     );
                dps_a.setText( dps_a_Value);
                if(unit_bouns_type_g[race][i]>0){
                    bouns_Value=bounsTrans(unit_bouns_type_g[race][i],unit_bouns_dmg_g[race][i],0);
                }else if((unit_bouns_type_a[race][i]>0)){
                    bouns_Value=bounsTrans(unit_bouns_type_a[race][i],unit_bouns_dmg_a[race][i],1);
                }
                else
                    bouns_Value="N/A";
                bouns.setText( bouns_Value);
                hppc.setText(Double.toString(  Math.round( (unit_hp[race][i]+unit_shield[race][i])*1.0/unit_costs[race][i]*100) *0.01 ));

                Double dps1,dps2;
                dps1=  Math.round(((unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])/unit_costs[race][i]*100) *0.01 ;
                dps2=  Math.round(((unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])/unit_costs[race][i]*100) *0.01 ;
                dpspc_g.setText(Double.toString(dps1));
                dpspc_a.setText(Double.toString(dps2));

                dps1=0.0;
                dps2=0.0;
                if(unit_bouns_type_g[race][i]>0)
                    dps1=  Math.round((( (unit_dmg_g[race][i]+unit_bouns_dmg_g[race][i])*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])*100) *0.01 ;
                else if(unit_bouns_type_a[race][i]>0)
                    dps2=  Math.round((( (unit_dmg_a[race][i]+unit_bouns_dmg_a[race][i])*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])*100) *0.01 ;

                if(dps1>dps2)
                    bdps.setText(Double.toString(dps1));
                else
                    bdps.setText(Double.toString(dps2));

                //--------------------------------------------upgrade control--------------------------------
                if(race==0){
                    lib_defON=false;

                    upgrade1.setImageResource(R.drawable.lib_adv);
                    // upgrade2.setImageResource(R.drawable.raven_shells);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.lib_defmode);
                    //skill2.setImageResource(R.drawable.BC_warp);
                    // skill3.setImageResource(R.drawable.raven_missile);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==1){
                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.INVISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==2){
                    //upgrade1.setImageResource(R.drawable.raven_reactor);
                    // upgrade2.setImageResource(R.drawable.raven_shells);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.viper_abduct);

                    skill2.setImageResource(R.drawable.viper_cloud);
                    skill3.setImageResource(R.drawable.viper_bomb);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.VISIBLE);
                    skill3.setVisibility(View.VISIBLE);
                }




            }
        });

        unit_14=(ImageButton)findViewById(R.id.imageButton14);
        unit_14.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                toggleFlag=false;int i=14;  if(current!=i){                     currentChange=true;                     current=i;                 }else                     currentChange=false;; updateUI();                if(race==1)                     shield.setText("("+ Integer.toString(unit_shield[race][i])  +")");                 else                     shield.setText("");
                if(race==0)
                    unit_14.setImageResource(R.drawable.thor_select);
                else if(race==1)
                    unit_14.setImageResource(R.drawable.carrier_select);
                else if(race==2)
                    unit_14.setImageResource(R.drawable.ultralisk_select);
                cost_Value=Integer.toString(unit_costs[race][i]);
                cost.setText(cost_Value);
                armor_Value=Integer.toString(unit_armor[race][i]);
                armor.setText(armor_Value);
                hp_Value=Integer.toString(unit_hp[race][i]);
                hp.setText(hp_Value);
                type_Value=typeTrans(unit_type[race][i]);
                type.setText(type_Value);
                range_a_Value=Integer.toString(unit_range_air[race][i]);
                range_a.setText( range_a_Value);
                range_g_Value=Integer.toString(unit_range_gnd[race][i]);
                if(unit_range_gnd[race][i]==1)                     range_g.setText( "Melee");                 else                     range_g.setText( range_g_Value);
                move_Value=Double.toString(unit_move[race][i]);
                move.setText( move_Value);
                dmg_g_Value=Integer.toString(unit_dmg_g[race][i]);
                if(unit_atks_g[race][i]>=2)
                    dmg_g_Value=dmg_g_Value+"x"+ Integer.toString(unit_atks_g[race][i]);
                dmg_g.setText( dmg_g_Value);
                dmg_a_Value=Integer.toString(unit_dmg_a[race][i]);
                if(unit_atks_a[race][i]>=2)
                    dmg_a_Value=dmg_a_Value+"x"+ Integer.toString(unit_atks_a[race][i]);
                dmg_a.setText( dmg_a_Value);
                atkSp_g_Value=Double.toString(unit_atkSp_g[race][i]);
                atkSp_g.setText( atkSp_g_Value);
                atkSp_a_Value=Double.toString(unit_atkSp_a[race][i]);
                atkSp_a.setText( atkSp_a_Value);
                dps_g_Value=Double.toString( (Math.round (     (unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i]*100)  ) *0.01     );
                dps_g.setText( dps_g_Value);
                dps_a_Value=Double.toString( (Math.round (     (unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i]*100)  ) *0.01     );
                dps_a.setText( dps_a_Value);
                if(unit_bouns_type_g[race][i]>0){
                    bouns_Value=bounsTrans(unit_bouns_type_g[race][i],unit_bouns_dmg_g[race][i],0);
                }else if((unit_bouns_type_a[race][i]>0)){
                    bouns_Value=bounsTrans(unit_bouns_type_a[race][i],unit_bouns_dmg_a[race][i],1);
                }
                else
                    bouns_Value="N/A";
                bouns.setText( bouns_Value);
                hppc.setText(Double.toString(  Math.round( (unit_hp[race][i]+unit_shield[race][i])*1.0/unit_costs[race][i]*100) *0.01 ));

                Double dps1,dps2;
                dps1=  Math.round(((unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])/unit_costs[race][i]*100) *0.01 ;
                dps2=  Math.round(((unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])/unit_costs[race][i]*100) *0.01 ;
                dpspc_g.setText(Double.toString(dps1));
                dpspc_a.setText(Double.toString(dps2));

                dps1=0.0;
                dps2=0.0;
                if(unit_bouns_type_g[race][i]>0)
                    dps1=  Math.round((( (unit_dmg_g[race][i]+unit_bouns_dmg_g[race][i])*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])*100) *0.01 ;
                else if(unit_bouns_type_a[race][i]>0)
                    dps2=  Math.round((( (unit_dmg_a[race][i]+unit_bouns_dmg_a[race][i])*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])*100) *0.01 ;

                if(dps1>dps2)
                    bdps.setText(Double.toString(dps1));
                else
                    bdps.setText(Double.toString(dps2));

                //--------------------------------------------upgrade control--------------------------------
                if(race==0){
                    //upgrade1.setImageResource(R.drawable.raven_reactor);
                    // upgrade2.setImageResource(R.drawable.raven_shells);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.thor_hmode);
                    // skill2.setImageResource(R.drawable.mine_missile);
                    // skill3.setImageResource(R.drawable.raven_missile);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==1){
                    upgrade1.setImageResource(R.drawable.carrier_catapult);
                    // upgrade2.setImageResource(R.drawable.raven_shells);
                    //upgrade3.setImageResource(R.drawable.);
                    //skill1.setImageResource(R.drawable.thor_hmode);
                    // skill2.setImageResource(R.drawable.mine_missile);
                    // skill3.setImageResource(R.drawable.raven_missile);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.INVISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==2){
                    upgrade1.setImageResource(R.drawable.ultralisk_armor);
                    // upgrade2.setImageResource(R.drawable.raven_shells);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.ultralisk_frenzied);
                    //skill2.setImageResource(R.drawable.raven_drone);
                    // skill3.setImageResource(R.drawable.raven_missile);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }



            }
        });

        unit_15=(ImageButton)findViewById(R.id.imageButton15);
        unit_15.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                toggleFlag=false;int i=15;  if(current!=i){                     currentChange=true;                     current=i;                 }else                     currentChange=false;; updateUI();                if(race==1)                     shield.setText("("+ Integer.toString(unit_shield[race][i])  +")");                 else                     shield.setText("");
                if(race==0)
                    unit_15.setImageResource(R.drawable.battlecruiser_select);
                else if(race==1)
                    unit_15.setImageResource(R.drawable.core);
                else if(race==2)
                    unit_15.setImageResource(R.drawable.broodlord_select);
                cost_Value=Integer.toString(unit_costs[race][i]);
                cost.setText(cost_Value);
                armor_Value=Integer.toString(unit_armor[race][i]);
                armor.setText(armor_Value);
                hp_Value=Integer.toString(unit_hp[race][i]);
                hp.setText(hp_Value);
                type_Value=typeTrans(unit_type[race][i]);
                type.setText(type_Value);
                range_a_Value=Integer.toString(unit_range_air[race][i]);
                range_a.setText( range_a_Value);
                range_g_Value=Integer.toString(unit_range_gnd[race][i]);
                if(unit_range_gnd[race][i]==1)                     range_g.setText( "Melee");                 else                     range_g.setText( range_g_Value);
                move_Value=Double.toString(unit_move[race][i]);
                move.setText( move_Value);
                dmg_g_Value=Integer.toString(unit_dmg_g[race][i]);
                if(unit_atks_g[race][i]>=2)
                    dmg_g_Value=dmg_g_Value+"x"+ Integer.toString(unit_atks_g[race][i]);
                dmg_g.setText( dmg_g_Value);
                dmg_a_Value=Integer.toString(unit_dmg_a[race][i]);
                if(unit_atks_a[race][i]>=2)
                    dmg_a_Value=dmg_a_Value+"x"+ Integer.toString(unit_atks_a[race][i]);
                dmg_a.setText( dmg_a_Value);
                atkSp_g_Value=Double.toString(unit_atkSp_g[race][i]);
                atkSp_g.setText( atkSp_g_Value);
                atkSp_a_Value=Double.toString(unit_atkSp_a[race][i]);
                atkSp_a.setText( atkSp_a_Value);
                dps_g_Value=Double.toString( (Math.round (     (unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i]*100)  ) *0.01     );
                dps_g.setText( dps_g_Value);
                dps_a_Value=Double.toString( (Math.round (     (unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i]*100)  ) *0.01     );
                dps_a.setText( dps_a_Value);
                if(unit_bouns_type_g[race][i]>0){
                    bouns_Value=bounsTrans(unit_bouns_type_g[race][i],unit_bouns_dmg_g[race][i],0);
                }else if((unit_bouns_type_a[race][i]>0)){
                    bouns_Value=bounsTrans(unit_bouns_type_a[race][i],unit_bouns_dmg_a[race][i],1);
                }
                else
                    bouns_Value="N/A";
                bouns.setText( bouns_Value);

                hppc.setText(Double.toString(  Math.round( (unit_hp[race][i]+unit_shield[race][i])*1.0/unit_costs[race][i]*100) *0.01 ));

                Double dps1,dps2;
                dps1=  Math.round(((unit_dmg_g[race][i]*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])/unit_costs[race][i]*100) *0.01 ;
                dps2=  Math.round(((unit_dmg_a[race][i]*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])/unit_costs[race][i]*100) *0.01 ;
                dpspc_g.setText(Double.toString(dps1));
                dpspc_a.setText(Double.toString(dps2));

                dps1=0.0;
                dps2=0.0;
                if(unit_bouns_type_g[race][i]>0)
                    dps1=  Math.round((( (unit_dmg_g[race][i]+unit_bouns_dmg_g[race][i])*unit_atks_g[race][i] )/ unit_atkSp_g[race][i])*100) *0.01 ;
                else if(unit_bouns_type_a[race][i]>0)
                    dps2=  Math.round((( (unit_dmg_a[race][i]+unit_bouns_dmg_a[race][i])*unit_atks_a[race][i] )/ unit_atkSp_a[race][i])*100) *0.01 ;

                if(dps1>dps2)
                    bdps.setText(Double.toString(dps1));
                else
                    bdps.setText(Double.toString(dps2));

                //--------------------------------------------upgrade control--------------------------------
                if(race==0){
                    upgrade1.setImageResource(R.drawable.bc_yamato);
                    // upgrade2.setImageResource(R.drawable.raven_shells);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.bc_yamato);
                    skill2.setImageResource(R.drawable.bc_warp);
                    // skill3.setImageResource(R.drawable.raven_missile);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.VISIBLE);
                    skill3.setVisibility(View.INVISIBLE);

                }else if(race==1){
                    upgrade1.setImageResource(R.drawable.mothership);
                    // upgrade2.setImageResource(R.drawable.raven_shells);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.core_timewarp);
                    //skill2.setImageResource(R.drawable.bc_warp);
                    // skill3.setImageResource(R.drawable.raven_missile);

                    upgrade1.setVisibility(View.VISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }else if(race==2){
                    //upgrade1.setImageResource(R.drawable.raven_reactor);
                    // upgrade2.setImageResource(R.drawable.raven_shells);
                    //upgrade3.setImageResource(R.drawable.);
                    skill1.setImageResource(R.drawable.broodlord_swarm);
                    //skill2.setImageResource(R.drawable.raven_drone);
                    // skill3.setImageResource(R.drawable.raven_missile);

                    upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
                    upgrade2.setVisibility(View.INVISIBLE);
                    upgrade3.setVisibility(View.INVISIBLE);
                    skill1.setVisibility(View.VISIBLE);
                    skill2.setVisibility(View.INVISIBLE);
                    skill3.setVisibility(View.INVISIBLE);
                }

            }
        });


        //mTextMessage = (TextView) findViewById(R.id.message);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        // navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    void updateUI(){

        mainBackground = (LinearLayout)findViewById(R.id.container1);
        upgrade1.setVisibility(View.INVISIBLE);  //One of VISIBLE, INVISIBLE, or GONE.
        upgrade2.setVisibility(View.INVISIBLE);
        upgrade3.setVisibility(View.INVISIBLE);
        skill1.setVisibility(View.INVISIBLE);
        skill2.setVisibility(View.INVISIBLE);
        skill3.setVisibility(View.INVISIBLE);
        if(race==0){
            soundPool.play(sound_terran, 1.0F, 1.0F, 0, 0, 1.0F);
            mainBackground.setBackgroundColor(Color.parseColor("#020f22"));
            unit_1.setImageResource(R.drawable.marine);
            unit_2.setImageResource(R.drawable.marauder);
            unit_3.setImageResource(R.drawable.reaper);
            unit_4.setImageResource(R.drawable.ghost);
            unit_5.setImageResource(R.drawable.hellbat);
            unit_6.setImageResource(R.drawable.medivac);
            unit_7.setImageResource(R.drawable.banshee);
            unit_8.setImageResource(R.drawable.viking);
            unit_9.setImageResource(R.drawable.raven);
            unit_10.setImageResource(R.drawable.tank);
            unit_11.setImageResource(R.drawable.mine);
            unit_12.setImageResource(R.drawable.cyclone);
            unit_13.setImageResource(R.drawable.liberator);
            unit_14.setImageResource(R.drawable.thor);
            unit_15.setImageResource(R.drawable.battlecruiser);
        }else if(race==1){
            soundPool.play(sound_protoss, 1.0F, 1.0F, 0, 0, 1.0F);

            mainBackground.setBackgroundColor(Color.parseColor("#020f22"));
            unit_1.setImageResource(R.drawable.zealot);
            unit_2.setImageResource(R.drawable.stalker);
            unit_3.setImageResource(R.drawable.sentry);
            unit_4.setImageResource(R.drawable.adept);
            if(ob_oracle==true)
                unit_5.setImageResource(R.drawable.ob);
            else
                unit_5.setImageResource(R.drawable.oracle);

            unit_6.setImageResource(R.drawable.darktemplar);
            unit_7.setImageResource(R.drawable.disruptor);
            unit_8.setImageResource(R.drawable.phoenix);
            unit_9.setImageResource(R.drawable.high_templer);
            unit_10.setImageResource(R.drawable.immortal);
            unit_11.setImageResource(R.drawable.voidray);
            unit_12.setImageResource(R.drawable.colossus);
            unit_13.setImageResource(R.drawable.tempest);
            unit_14.setImageResource(R.drawable.carrier);
            unit_15.setImageResource(R.drawable.core);

        }else if(race==2){
            soundPool.play(sound_zerg, 0.2F, 0.2F, 0, 0, 1.0F);
            // mainBackground.setBackgroundColor(Color.parseColor("#FF020A1B"));
            unit_1.setImageResource(R.drawable.zergling);
            unit_2.setImageResource(R.drawable.baneling);
            unit_3.setImageResource(R.drawable.roach);
            unit_4.setImageResource(R.drawable.queens);
            unit_5.setImageResource(R.drawable.overseer);
            unit_6.setImageResource(R.drawable.hydralisk);
            unit_7.setImageResource(R.drawable.mutalisk);
            unit_8.setImageResource(R.drawable.corruptor);
            unit_9.setImageResource(R.drawable.infestor);
            unit_10.setImageResource(R.drawable.host);
            unit_11.setImageResource(R.drawable.lurker);
            unit_12.setImageResource(R.drawable.ravager);
            unit_13.setImageResource(R.drawable.viper);
            unit_14.setImageResource(R.drawable.ultralisk);
            unit_15.setImageResource(R.drawable.broodlord);
        }

    }




    String bounsTrans(int type, int dmg,int GorA){ //type: 0 none 1 light 2 Armored 3 Bio 4 Mec 5 Psi,
        String s="";

        if(GorA==0 && type!=0)// gnd
            s=s+"Gnd-";
        else if(GorA==1 && type!=0)// gnd
            s=s+"Air-";
        else if(GorA==3 && type!=0)
            s=s;
        else
            return "N/A";

        if(type==1)
            s=s+"Lig+";
        else if(type==2)
            s=s+"Arm+";
        else if(type==3)
            s=s+"Bio+";
        else if(type==4)
            s=s+"Mec+";
        else if(type==5)
            s=s+"Psi+";
        else  if(type==6)
            s=s+"Ma+";
        else
            return "N/A";

        s=s+Integer.toString(dmg);

        return s;
    }

    // 0 light 1 Armored 2 none 3 psi, 0 bio 1 mec 2 none 3 psi 4 massive 5 Bio-massive 6 Mec-massive 7 Bio-Mec 8 Bio-psi 9 mec-psi
    String typeTrans(int input){
        String s="";
        if(input/10==0)
            s=s+"Light";
        else if(input/10==1)
            s=s+"Armored";
        else if(input/10==3)
            s=s+"Psi";
        else if(input/10==4)
            s=s+"Bio";

        s=s+"-";

        if(input%10==0)
            s=s+"Bio";
        else if(input%10==1)
            s=s+"Mec";
        else if(input%10==3)
            s=s+"Psi";
        else if(input%10==4)
            s=s+"Ma";
        else if(input%10==5)
            s=s+"Bio-Ma";
        else if(input%10==6)
            s=s+"Mec-Ma";
        else if(input%10==7)
            s=s+"Mec-Bio";
        else if(input%10==8)
            s=s+"Bio-Psi";
        else if(input%10==9)
            s=s+"Mec-Psi";
        return s;
    }

}
