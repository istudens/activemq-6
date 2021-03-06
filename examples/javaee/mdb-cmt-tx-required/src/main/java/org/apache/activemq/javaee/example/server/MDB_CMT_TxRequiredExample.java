/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.javaee.example.server;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.Calendar;

/**
 * @author <a href="mailto:andy.taylor@jboss.org">Andy Taylor</a>
 */
@MessageDriven(name = "MDB_CMT_TxRequiredExample",
               activationConfig =
                  {
                     @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                     @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/testQueue"),
                     @ActivationConfigProperty(propertyName = "consumerMaxRate", propertyValue = "1")
                  })
public class MDB_CMT_TxRequiredExample implements MessageListener
{

   public void onMessage(final Message message)
   {
      try
      {
         // Step 9. We know the client is sending a text message so we cast
         TextMessage textMessage = (TextMessage) message;

         // Step 10. get the text from the message.
         String text = textMessage.getText();

         Calendar c = Calendar.getInstance();

         System.out.println("message " + text + " received at " + c.getTime());

      }
      catch (JMSException e)
      {
         e.printStackTrace();
      }
   }
}
