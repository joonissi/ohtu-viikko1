package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto huonoVarasto;
    Varasto kuormitettuVarasto;
    Varasto kuormitettuHuonoVarasto;
    Varasto kuormitettuYliMenevaSaldoVarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        huonoVarasto = new Varasto(-1);

        kuormitettuVarasto = new Varasto(5, 5);
        kuormitettuHuonoVarasto = new Varasto(-5, -5);
        kuormitettuYliMenevaSaldoVarasto = new Varasto(5, 6);
    }

    @Test
    public void varastoToStringMetodiToimii() {
      assertEquals("saldo = 5.0, vielä tilaa 0.0", kuormitettuVarasto.toString());
    }

    @Test
    public void kuormitettuVarastoToimiiTilavuudelta() {
      assertEquals(0, kuormitettuHuonoVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuVarastoToimiiNegatiivisellaTilavuudelta() {
      assertEquals(5, kuormitettuVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuVarastoToimiiJaYlimaarainenHukataan() {
      assertEquals(5, kuormitettuYliMenevaSaldoVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void yritetaanLuodaVarastoNegatiivisellaTilavuudella() {
      
      assertEquals(0, huonoVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void yritaLisataNegatiivinenSaldoVarastoon() {
      varasto.lisaaVarastoon(-1);

      assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void yritaOttaanegatiivinenSaldoVarastosta() {
      varasto.lisaaVarastoon(5);

      varasto.otaVarastosta(-1);

      assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void yritaLaittaaaLiikaaVarastoon() {
      varasto.lisaaVarastoon(11);

      assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void yritaOttaaLiikaaVarastosta() {
      varasto.lisaaVarastoon(9);

      varasto.otaVarastosta(10);

      assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}