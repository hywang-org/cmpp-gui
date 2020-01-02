package cn.sanenen.service;

import java.awt.event.ActionEvent;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.zx.sms.codec.cmpp.msg.CmppSubmitRequestMessage;
import com.zx.sms.common.util.MsgId;
import com.zx.sms.connect.manager.EndpointConnector;
import com.zx.sms.connect.manager.EndpointManager;
import com.zx.sms.connect.manager.EndpointEntity.SupportLongMessage;
import com.zx.sms.connect.manager.cmpp.CMPPClientEndpointEntity;
import com.zx.sms.handler.api.BusinessHandlerInterface;

import cn.hutool.core.thread.ThreadUtil;
import cn.sanenen.entity.Channel;
import cn.sanenen.handler.MessageReceiveHandler;
import io.netty.channel.ChannelFuture;

/**
 * 发送服务类 2
 * 
 * @author sun 2019年4月23日 下午2:25:34
 */
public class ConvertService {
	
	private final static EndpointManager manager = EndpointManager.INS;

	public static void sendSms(CmppSubmitRequestMessage msg) {

		MsgId msgId = new MsgId();
		msg.setMsgid(msgId);
		EndpointConnector<?> connector = EndpointManager.INS.getEndpointConnector(msg.getSrcId());
		while (true) {
			ChannelFuture write = connector.asynwrite(msg);
			if (write != null) {
				break;
			} else {
				ThreadUtil.sleep(10);
			}
		}
	}
	
	public static void actionPerformed(ActionEvent e) {
		// 连接断开
		manager.close();
	}
	
	public static void connect(Channel channle) {
		CMPPClientEndpointEntity client = new CMPPClientEndpointEntity();
		client.setId(channle.getAccessNo());
		client.setHost(channle.getIp());
		client.setPort(channle.getPort());
		client.setUserName(channle.getAccessNo());
		client.setPassword(channle.getLoginPwd());
		client.setServiceId(channle.getAccessNo());
		client.setMaxChannels(Short.parseShort("1"));
		client.setVersion(channle.getCmppVersion().shortValue());
		client.setWriteLimit(channle.getSpeedLimit());

		client.setChartset(Charset.forName("utf-8"));
		client.setRetryWaitTimeSec((short) 30);
		client.setUseSSL(false);
		client.setMaxRetryCnt((short) 0);
		client.setReSendFailMsg(false);
		client.setSupportLongmsg(SupportLongMessage.BOTH);
		List<BusinessHandlerInterface> clienthandlers = new ArrayList<BusinessHandlerInterface>();
		clienthandlers.add(new MessageReceiveHandler());
		client.setBusinessHandlerSet(clienthandlers);

		manager.addEndpointEntity(client);
		ThreadUtil.sleep(1000);
		for (int i = 0; i < client.getMaxChannels(); i++)
			manager.openEndpoint(client);
	}

	public static String getMobile() {
		StringBuffer mobile = new StringBuffer();
		// var reg = /^(1[3|5|7|8][0-9]{9})|(1[4][7|5][0-9]{8})$/;
		int arr[] = {
				// 1390105,1342600,1350100,1360100,1370100,1380000
				135, 136, 137, 138, 139, 147, 150, 151, 152, 154, 157, 158, 159, 182, 183, 184, 187, 188, 172, 178,
				1703, 1705, 1706, 1340, 1341, 1342, 1343, 1344, 1345, 1346, 1347, 1348, 130, 131, 132, 155, 156, 185,
				186, 145, 175, 176, 1707, 1708, 1709, 133, 153, 173, 177, 180, 181, 189, 1700, 1701, 1349 };// 定义一个数组
																											// 1、获取数组长度
		int len = arr.length;// 获取数组长度给变量len
		// 2、根据数组长度，使用Random随机数组的索引值
		Random random = new Random();// 创建随机对象
		int arrIdx = random.nextInt(len - 1);// 随机数组索引，nextInt(len-1)表示随机整数[0,(len-1)]之间的值
		// 3、根据随机索引获取数组值
		int startNum = arr[arrIdx];// 获取数组值
		mobile.append(startNum + "");
		for (int i = 0; i < 11 - (startNum + "").length(); i++) {
			mobile.append(random.nextInt(9) + "");
		}
		return mobile.toString();
	}

	public static String getValidataCode() {
		StringBuffer code = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			code.append(new Random().nextInt(9));
		}
		return code.toString();
	}
}
