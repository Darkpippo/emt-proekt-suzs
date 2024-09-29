package mk.ukim.finki.emt.billingpayment.xport.events;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.billingpayment.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointmentEventListener {
    private PaymentService paymentService;

//    @KafkaListener(topics = "appointments-topic", groupId = "billingpayment")
//    public void createPaymentEntity(AppointmentCreatedEvent event) {
//        try {
//            Long appointmentId = event.getAppointmentId();
//            Long patientId = event.getPatientId();
//            Long doctorId = event.getDoctorId();
//            String status = event.getStatus();
//
//            Payment payment = new Payment();
//            payment.setAppointmentId(appointmentId);
//            payment.setStatus("Not paid");
//            payment.setDate(LocalDateTime.now());
//
//            paymentService.createPayment(payment);
//
//            System.out.println("Payment created for appointment: " + appointmentId);
//        } catch (Exception e) {
//            // Handle exceptions, possibly log them
//            System.err.println("Failed to create payment for appointment: " + event.getAppointmentId());
//            e.printStackTrace();
//        }
//    }
}
