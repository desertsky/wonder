//
// ERCNNotificationCoordinator.java
// Project ERChangeNotificationJMS
//
// Created by tatsuya on Sat Mar 6 2004
//
package er.changenotification;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSLog;
import com.webobjects.foundation.NSPropertyListSerialization;

import java.util.Properties;

final class ERCNConfigurationV2 extends ERCNConfiguration {

    ERCNConfigurationV2(Properties props) {

        setEntitiesNotToSynchronize((NSArray)NSPropertyListSerialization.propertyListFromString(
                props.getProperty(propertyPrefix()+ ".entitiesNotToSynchronize",System.getProperty(PROPERTY_PREFIX+ ".entitiesNotToSynchronize"))));
    	
        NSLog.out.appendln("Prefix "+propertyPrefix());

        setChangeTypesToPublish((NSArray)NSPropertyListSerialization.propertyListFromString(
        		props.getProperty(propertyPrefix()+ ".changeTypesToPublish",System.getProperty(PROPERTY_PREFIX+ ".changeTypesToPublish"))));

        setChangeTypesToSubscribe((NSArray)NSPropertyListSerialization.propertyListFromString(
                props.getProperty(propertyPrefix() + ".changeTypesToSubscribe",System.getProperty(PROPERTY_PREFIX+ ".changeTypesToSubscribe"))));

        setTopicName(props.getProperty(propertyPrefix() + ".jms.topicName",System.getProperty(PROPERTY_PREFIX+".jms.topicName")));
        
        setTopicConnectionFactory(props.getProperty(propertyPrefix()+ ".jms.topicConnectionFactory",System.getProperty(PROPERTY_PREFIX + ".jms.topicConnectionFactory", "JmsTopicConnectionFactory")));
       
        setUserName(props.getProperty(propertyPrefix() + ".jms.userName",System.getProperty(PROPERTY_PREFIX+".jms.userName")));
        setPassword(props.getProperty(propertyPrefix() + ".jms.password",System.getProperty(PROPERTY_PREFIX+".jms.password")));

        setConnectionRecoveryInterval(props.getProperty(propertyPrefix()+ ".connectionRecoveryInterval",System.getProperty(PROPERTY_PREFIX + ".connectionRecoveryInterval")));
        setDisconnectionWarningInterval(props.getProperty(propertyPrefix() + ".disconnectionWarningInterval",System.getProperty(PROPERTY_PREFIX + ".disconnectionWarningInterval")));
        setIsSubscriberDurable("true".equals(props.getProperty(propertyPrefix() + ".jms.durableSubscribers",System.getProperty(PROPERTY_PREFIX + ".jms.durableSubscribers"))));
        
        setErcnDebugEnabled("true".equals(props.getProperty(propertyPrefix() + ".ercnDebugEnabled",System.getProperty(PROPERTY_PREFIX + ".ercnDebugEnabled"))));

        setProviderURL(props.getProperty(propertyPrefix() + ".jms.providerURL",System.getProperty(PROPERTY_PREFIX + ".jms.providerURL", "rmi://localhost:1099/JndiServer")));


        setInitialContextFactory(props.getProperty(propertyPrefix() + ".jms.initialContextFactory",System.getProperty(PROPERTY_PREFIX + ".jms.initialContextFactory",
                "org.exolab.jms.jndi.rmi.RmiJndiInitialContextFactory")));

    }

}

