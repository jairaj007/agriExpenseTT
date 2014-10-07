/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-07-22 21:53:01 UTC)
 * on 2014-10-07 at 20:31:45 UTC 
 * Modify at your own risk.
 */

package com.example.agriexpensett.cycleuseendpoint.model;

/**
 * Model definition for CycleUse.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the cycleuseendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class CycleUse extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String account;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double amount;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double cost;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer cycleid;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Key key;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String keyrep;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer purchaseId;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String resource;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getAccount() {
    return account;
  }

  /**
   * @param account account or {@code null} for none
   */
  public CycleUse setAccount(java.lang.String account) {
    this.account = account;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getAmount() {
    return amount;
  }

  /**
   * @param amount amount or {@code null} for none
   */
  public CycleUse setAmount(java.lang.Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getCost() {
    return cost;
  }

  /**
   * @param cost cost or {@code null} for none
   */
  public CycleUse setCost(java.lang.Double cost) {
    this.cost = cost;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getCycleid() {
    return cycleid;
  }

  /**
   * @param cycleid cycleid or {@code null} for none
   */
  public CycleUse setCycleid(java.lang.Integer cycleid) {
    this.cycleid = cycleid;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public CycleUse setId(java.lang.Integer id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Key getKey() {
    return key;
  }

  /**
   * @param key key or {@code null} for none
   */
  public CycleUse setKey(Key key) {
    this.key = key;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getKeyrep() {
    return keyrep;
  }

  /**
   * @param keyrep keyrep or {@code null} for none
   */
  public CycleUse setKeyrep(java.lang.String keyrep) {
    this.keyrep = keyrep;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getPurchaseId() {
    return purchaseId;
  }

  /**
   * @param purchaseId purchaseId or {@code null} for none
   */
  public CycleUse setPurchaseId(java.lang.Integer purchaseId) {
    this.purchaseId = purchaseId;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getResource() {
    return resource;
  }

  /**
   * @param resource resource or {@code null} for none
   */
  public CycleUse setResource(java.lang.String resource) {
    this.resource = resource;
    return this;
  }

  @Override
  public CycleUse set(String fieldName, Object value) {
    return (CycleUse) super.set(fieldName, value);
  }

  @Override
  public CycleUse clone() {
    return (CycleUse) super.clone();
  }

}
