package ohtuesimerkki;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

public class StatisticsTest {

  Reader readerStub = new Reader() {

    public List<Player> getPlayers() {
      ArrayList<Player> players = new ArrayList<Player>();

      players.add(new Player("Semenko", "EDM", 4, 12));
      players.add(new Player("Lemieux", "PIT", 45, 54));
      players.add(new Player("Kurri", "EDM", 37, 53));
      players.add(new Player("Yzerman", "DET", 42, 56));
      players.add(new Player("Gretzky", "EDM", 35, 89));

      return players;
    }
  };

  Statistics stats;

  @Before
  public void setUp() {
    // luodaan Statistics-olio joka käyttää "stubia"
    stats = new Statistics(readerStub);
  }

  // TODO add test methods here.
  // The methods must be annotated with annotation @Test. For example:
  //
  //@Test
    // public void luodaanStatsitOikein() {
    //     //stats

    //     // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
    //     assertEquals(4, stats);
    // }

  @Test 
  public void etsitaanPelaajaYksi() {
    assertSame("Semenko", stats.search("Semenko").getName());
  }

  @Test
  public void etsitaanPelaajaJotaEiOle() {
    assertEquals(null, stats.search("koss"));
  }

  @Test
  public void joukkueOnSama () {
    assertEquals("Lemieux", stats.team("PIT").get(0).getName());
  }

  @Test
  public void parasMaalinTekija() {
    assertEquals("Gretzky", stats.topScorers(0).get(0).getName());
  }
}