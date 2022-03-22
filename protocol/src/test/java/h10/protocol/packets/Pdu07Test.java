package h10.protocol.packets;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class Pdu07Test {

    @Test
    public void whenApParse() {
        String in = "IWAP07,20140818064408,1,1,697,#!AMR\n\u0004c<Зр\u0003\u00049яа\n\u00043яа\n\u00043яа\n\u00043яа\n\u00043яа\n\f\u0003џЊ\n\b\u0003ям\n\u00043яа\n\u0004B\b/\fл\u001Aж\u0005Їн[\n!T\u0004GЉ_®УЗ™(]зT\u001C\u0004\u00ADLg}а¦¬\u0016а+X\u0006\u0004%\u0016eЋЖУ‹;ZB@Њ\u0004\u001BAЪ®ъХЪ«v€Т^\u0004`Ы_ОъблЕЮ\n\u0004-<foљЪjќлuЃ\u0016\u0004\u0001Sf>кп‹Южm\u0007О\u0004g:eЅк‡»іGS\u0002Ф\u0004`\u001EfЇКЪZДтqu\u0006\u0004\u0002\u000Ffџй\u0010”ЕwZd\u001E\u0004\u001AQbnй»…9#QлB\u0004\u001A:^^ЛЫ¦}\"® а\u0004ќ±WџйкTВЮХ°n\u0004А\u0014Z®Эг’Жw™Ї–\u0004ќwYпэЖB…їWA4\u0004\u0019кZЬщЯen-\u0011цH\u0004\u0002Мg\u001Cщѓ\u0015ДD)cr\u00041Ak_ввrPЧiGШ\u0004!ЛHМшЌїT‚З\u0005>\u0004\u0019Ћ_?щЉ4\u00ADlЙ\u001A¤\u0004\u001C(_Пко%Н(МњJ\u0004gђdюљЧЄрX\u0014fа\u0004\u0003'eїЛfe\u0002\u007FЙ/„\u0004\u00AD:Z_ќЪcІIђр,\u0004\u00AD\u0014Wnл‹§SVГ#p\u0004аQ\\мйз\n\u0004\u00ADdfЇђЫ(  +пj\u0004;\u007FlЇніcО¶\nхґ\u0004\u0010јpЇчД†И§пC>\u0004\u0003YШпз‰g\ni\u0013х~\u0004\u0010\u007FbЇйп5Ћ…+R|\u0004\u0010Yf\u00ADяЗ«2ш#";
        ProtocolDataUnit pdu = new ProtocolDataUnit();
        pdu.parse(in);
        AP07 content = pdu.getContent();
        byte[] audio = content.getAudio();
        assertEquals(LocalDateTime.of(2014, 8, 18, 6, 44, 8), content.getDateTime());
        assertEquals(1, content.getTotalPackets());
        assertEquals(1, content.getNumber());
        assertEquals(697, audio.length);
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBP07,20140818064408,6,1,1#";
        BP07 content = new BP07();
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP07", content);
        content.setDateTime(LocalDateTime.of(2014, 8, 18, 6, 44, 8));
        content.setTotalPackets(6);
        content.setNumber(1);
        content.setSuccess(true);
        assertEquals(expected, pdu.toPacket());
    }
}
