import java.util.Random;

public class Computer {
	String data;
	TCPSegment tcpSegment;

	public Computer() {

	}

	public TCPSegment inputData(String data, TCPSegment tcpSegment) {
		this.data = data;
		this.tcpSegment = tcpSegment;
		this.tcpSegment.setPayloadData(data); // TCPSegment�� payload(data)�� ����.
		return tcpSegment; // Segment ��ȯ
	}

	public void outputData(TCPSegment tcpSegment) {
		data = tcpSegment.payloadData; // TCPSegment�� payload(data)�� ������.
		int temp, temp2;
		temp = tcpSegment.getAcknowledgementNumber(); // Ack �� ����
		temp2 = tcpSegment.getSequenceNumber(); // Seq �� ����

		tcpSegment.setAcknowledgementNumber(temp2 + 1); // ACK ���� Seq �� +1�� ����
		tcpSegment.setSequenceNumber(temp); // Seq���� ACK ������ ����
		tcpSegment.getTcpFlag().setACK(0); // ACK�� 0 ����

	}

}
