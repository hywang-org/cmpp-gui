package cn.sanenen.handler;

import com.zx.sms.codec.cmpp.msg.CmppDeliverRequestMessage;
import com.zx.sms.codec.cmpp.msg.CmppDeliverResponseMessage;
import com.zx.sms.codec.cmpp.msg.CmppSubmitRequestMessage;
import com.zx.sms.codec.cmpp.msg.CmppSubmitResponseMessage;
import com.zx.sms.handler.api.AbstractBusinessHandler;
import com.zx.sms.session.cmpp.SessionState;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.sanenen.MainWindow;
import cn.sanenen.service.AtomicUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;

@Sharable
public class MessageReceiveHandler extends AbstractBusinessHandler {

	@Override
	public String name() {
		return "MessageReceiveHandler-smsBiz";
	}

	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt == SessionState.Connect) {
			MainWindow.button_send.setEnabled(true);
			MainWindow.button_connect.setEnabled(false);
		}
		ctx.fireUserEventTriggered(evt);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// System.out.println(msg);
		if (msg instanceof CmppDeliverRequestMessage) {
			CmppDeliverRequestMessage e = (CmppDeliverRequestMessage) msg;
			Console.log("111:", e);
			Console.log("222:", new String(e.getMsgContentBytes(), "iso-10646-ucs-2"));
			System.out.println("client CmppDeliverRequestMessage = " + e);
			System.out.println("client CmppDeliverRequestMessage serviceId = " + e.getServiceid());
			CmppDeliverResponseMessage responseMessage = new CmppDeliverResponseMessage(e.getHeader().getSequenceId());
			MainWindow.label_reportCount.setText(StrUtil.format("报告数量:{}", AtomicUtil.reportCount.incrementAndGet()));
			responseMessage.setResult(0);
			ctx.channel().writeAndFlush(responseMessage);
			// cnt.incrementAndGet();

		} else if (msg instanceof CmppDeliverResponseMessage) {
			// CmppDeliverResponseMessage e = (CmppDeliverResponseMessage) msg;

		} else if (msg instanceof CmppSubmitRequestMessage) {
			CmppSubmitRequestMessage e = (CmppSubmitRequestMessage) msg;
			CmppSubmitResponseMessage resp = new CmppSubmitResponseMessage(e.getHeader().getSequenceId());
			// resp.setResult(RandomUtils.nextInt()%1000 <10 ? 8 : 0);
			resp.setResult(0);
			ctx.channel().writeAndFlush(resp);
		} else if (msg instanceof CmppSubmitResponseMessage) {
			CmppSubmitResponseMessage e = (CmppSubmitResponseMessage) msg;
			System.out.println("client CmppSubmitResponseMessage = " + msg);
			MainWindow.label_reponseCount.setText(StrUtil.format("响应数量:{}", AtomicUtil.reponseCount.incrementAndGet()));
			long result = e.getResult();
			if (result == 0) {
				MainWindow.label_sucCount.setText(StrUtil.format("提交成功:{}", AtomicUtil.sucCount.incrementAndGet()));
			} else {
				MainWindow.label_reponseCount
						.setText(StrUtil.format("提交失败:{}", AtomicUtil.failCount.incrementAndGet()));
			}
		} else {
			ctx.fireChannelRead(msg);
		}
	}

	public MessageReceiveHandler clone() throws CloneNotSupportedException {
		MessageReceiveHandler ret = (MessageReceiveHandler) super.clone();
		return ret;
	}

}
