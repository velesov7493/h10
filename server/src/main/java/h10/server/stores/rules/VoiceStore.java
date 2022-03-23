package h10.server.stores.rules;

import h10.server.models.Voice;

import java.util.List;

public interface VoiceStore extends Store<Integer, Voice> {

    Voice findFirstByImei(String imei);

    List<Voice> findAllByImei(String imei);

    int getCountByImei(String imei);
}
