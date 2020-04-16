package com.knbdtu.reservations.web.application;

import com.knbdtu.reservations.business.domain.RoomReservation;
import com.knbdtu.reservations.business.service.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @MockBean
    private ReservationService reservationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getReservations() throws Exception {
        Date date = DATE_FORMAT.parse("2020-04-11");
        List<RoomReservation> mockReservationList = new ArrayList<>();

        RoomReservation mockRoomReservation = new RoomReservation();
        mockRoomReservation.setLastName("Test");
        mockRoomReservation.setFirstName("JUnit");
        mockRoomReservation.setDate(date);
        mockRoomReservation.setGuestId(1);
        mockRoomReservation.setRoomId(100);
        mockRoomReservation.setRoomNumber("J1");
        mockRoomReservation.setRoomName("JUnit Room");
        mockReservationList.add(mockRoomReservation);

        given(reservationService.getRoomReservationsForDate(date)).willReturn(mockReservationList);
        this.mockMvc.perform(get("/reservations?date=2020-04-11"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Test, JUnit")));
    }
}
