/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hbase.chaos.actions;

import org.apache.hadoop.hbase.HServerAddress;
import org.apache.hadoop.hbase.zookeeper.ZooKeeperWrapper;

/**
 * Action that tries to restart the active master.
 */
public class RestartActiveMasterAction extends RestartActionBaseAction {
  public RestartActiveMasterAction(long sleepTime) {
    super(sleepTime);
  }

  @Override
  public void perform() throws Exception {
    LOG.info("Performing action: Restart active master");

    ZooKeeperWrapper zkw = context.getHBaseIntegrationTestingUtility()
        .getHBaseAdmin().getConnection().getZooKeeperWrapper();

    HServerAddress masterLocation = zkw.readMasterAddress(null);

    restartMaster(masterLocation, sleepTime);
  }
}