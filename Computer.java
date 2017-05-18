import java.util.Random;

public class Computer {
	String data;
	TCPSegment tcpSegment;

	public Computer() {

	}

	public TCPSegment inputData(String data, TCPSegment tcpSegment) {
		this.data = data;
		this.tcpSegment = tcpSegment;
		this.tcpSegment.setPayloadData(data); // TCPSegment에 payload(data)를 지정.
		return tcpSegment; // Segment 반환
	}

	public void outputData(TCPSegment tcpSegment) {
		data = tcpSegment.payloadData; // TCPSegment에 payload(data)를 빼낸다.
		int temp, temp2;
		temp = tcpSegment.getAcknowledgementNumber(); // Ack 값 저장
		temp2 = tcpSegment.getSequenceNumber(); // Seq 값 저장

		tcpSegment.setAcknowledgementNumber(temp2 + 1); // ACK 값을 Seq 값 +1로 변경
		tcpSegment.setSequenceNumber(temp); // Seq값을 ACK 값으로 변경
		tcpSegment.getTcpFlag().setACK(0); // ACK값 0 세팅

	}

}
