/*
* Created by DSL Platform
* v1.7.6179.17358 
*/

package dsl_json.json;



public class ExternalSerialization   implements com.dslplatform.json.Configuration {
	
	
	@SuppressWarnings("unchecked")
	public void configure(final com.dslplatform.json.DslJson json) {
		setup(json);
	}

	@SuppressWarnings("unchecked")
	public static void setup(final com.dslplatform.json.DslJson json) {
		
		
		json.registerReader(com.cowtowncoder.jsonperf.dzone.MeasurementRecord.class, JSON_READER_struct1);
		json.registerWriter(com.cowtowncoder.jsonperf.dzone.MeasurementRecord.class, new com.dslplatform.json.JsonWriter.WriteObject<com.cowtowncoder.jsonperf.dzone.MeasurementRecord>() {
			@Override
			public void write(com.dslplatform.json.JsonWriter writer, com.cowtowncoder.jsonperf.dzone.MeasurementRecord value) {
				serialize(value, writer, json.omitDefaults);
			}
		});
		
		json.registerReader(com.cowtowncoder.jsonperf.dzone.MeasurementPOJO.class, JSON_READER_struct0);
		json.registerWriter(com.cowtowncoder.jsonperf.dzone.MeasurementPOJO.class, new com.dslplatform.json.JsonWriter.WriteObject<com.cowtowncoder.jsonperf.dzone.MeasurementPOJO>() {
			@Override
			public void write(com.dslplatform.json.JsonWriter writer, com.cowtowncoder.jsonperf.dzone.MeasurementPOJO value) {
				serialize(value, writer, json.omitDefaults);
			}
		});
	}
	
	public static void serialize(final com.cowtowncoder.jsonperf.dzone.MeasurementRecord self, final com.dslplatform.json.JsonWriter sw, final boolean minimal) {
		sw.writeByte(com.dslplatform.json.JsonWriter.OBJECT_START);
		if (minimal) {
			__serializeJsonObjectMinimal(self, sw, false);
		} else {
			__serializeJsonObjectFull(self, sw, false);
		}
		sw.writeByte(com.dslplatform.json.JsonWriter.OBJECT_END);
	}

	static void __serializeJsonObjectMinimal(final com.cowtowncoder.jsonperf.dzone.MeasurementRecord self, com.dslplatform.json.JsonWriter sw, boolean hasWrittenProperty) {
		
		
			if (self.getDuration() != 0L) {
			hasWrittenProperty = true;
				sw.writeAscii("\"duration\":", 11);
				com.dslplatform.json.NumberConverter.serialize(self.getDuration(), sw);
			}
		
			if (self.getTime() != 0L) {
			if(hasWrittenProperty) sw.writeByte(com.dslplatform.json.JsonWriter.COMMA);
			hasWrittenProperty = true;
				sw.writeAscii("\"time\":", 7);
				com.dslplatform.json.NumberConverter.serialize(self.getTime(), sw);
			}
		
		if(self.getType() != null) {
			if(hasWrittenProperty) sw.writeByte(com.dslplatform.json.JsonWriter.COMMA);
			hasWrittenProperty = true;
			sw.writeAscii("\"type\":\"", 8);
			sw.writeAscii(self.getType().name());
			sw.writeByte(com.dslplatform.json.JsonWriter.QUOTE);
		}
		
			if (self.getMeasurementId() != null) {
			if(hasWrittenProperty) sw.writeByte(com.dslplatform.json.JsonWriter.COMMA);
			hasWrittenProperty = true;
				sw.writeAscii("\"measurementId\":", 16);
				sw.writeString(self.getMeasurementId());
			}
	}

	static void __serializeJsonObjectFull(final com.cowtowncoder.jsonperf.dzone.MeasurementRecord self, com.dslplatform.json.JsonWriter sw, boolean hasWrittenProperty) {
		
		
			
			sw.writeAscii("\"duration\":", 11);
			com.dslplatform.json.NumberConverter.serialize(self.getDuration(), sw);
		
			
			sw.writeAscii(",\"time\":", 8);
			com.dslplatform.json.NumberConverter.serialize(self.getTime(), sw);
		
		
		if(self.getType() != null) {
			sw.writeAscii(",\"type\":\"", 9);
			sw.writeAscii(self.getType().name());
			sw.writeByte(com.dslplatform.json.JsonWriter.QUOTE);
		} else {
			sw.writeAscii(",\"type\":null", 12);
		}
		
			
			if (self.getMeasurementId() != null) {
				sw.writeAscii(",\"measurementId\":", 17);
				sw.writeString(self.getMeasurementId());
			} else {
				sw.writeAscii(",\"measurementId\":null", 21);
			}
	}

	public static final com.dslplatform.json.JsonReader.ReadObject<com.cowtowncoder.jsonperf.dzone.MeasurementRecord> JSON_READER_struct1 = new com.dslplatform.json.JsonReader.ReadObject<com.cowtowncoder.jsonperf.dzone.MeasurementRecord>() {
		@SuppressWarnings("unchecked")
		@Override
		public com.cowtowncoder.jsonperf.dzone.MeasurementRecord read(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
			if(reader.last() != '{') {
				throw new java.io.IOException("Expecting \'{\' at position " + reader.positionInStream() + ". Found " + (char)reader.last());
			}
			reader.getNextToken();
			final com.cowtowncoder.jsonperf.dzone.MeasurementRecord instance = new com.cowtowncoder.jsonperf.dzone.MeasurementRecord();
			deserialize(instance, reader);
			return instance;
		}
	};

	@SuppressWarnings("unchecked")
	static com.cowtowncoder.jsonperf.dzone.MeasurementRecord deserializestruct1(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
		final com.cowtowncoder.jsonperf.dzone.MeasurementRecord instance = new com.cowtowncoder.jsonperf.dzone.MeasurementRecord();
		deserialize(instance, reader);
		return instance;
	}

	@SuppressWarnings("unchecked")
	static void deserialize(final com.cowtowncoder.jsonperf.dzone.MeasurementRecord instance, final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
		
		long _duration_ = 0L;
		long _time_ = 0L;
		com.cowtowncoder.jsonperf.dzone.MeasurementType _type_ = null;
		String _measurementId_ = null;
		byte nextToken = reader.last();
		if(nextToken != '}') {
			int nameHash = reader.fillName();
			nextToken = reader.getNextToken();
			if(nextToken == 'n') {
				if (reader.wasNull()) {
					nextToken = reader.getNextToken();
				} else {
					throw new java.io.IOException("Expecting 'u' (as null) at position " + reader.positionInStream() + ". Found " + (char)nextToken);
				}
			} else {
				switch(nameHash) {
					
					case 799079693:
						_duration_ = com.dslplatform.json.NumberConverter.deserializeLong(reader);
					nextToken = reader.getNextToken();
						break;
					case 1564253156:
						_time_ = com.dslplatform.json.NumberConverter.deserializeLong(reader);
					nextToken = reader.getNextToken();
						break;
					case 1361572173:
						
					if (nextToken == '"') {
						switch(reader.calcHash()) {
							case -678320345: _type_ = com.cowtowncoder.jsonperf.dzone.MeasurementType.WEB_REQUEST; break;
							case 18818719: _type_ = com.cowtowncoder.jsonperf.dzone.MeasurementType.SQL; break;
							case -602493290: _type_ = com.cowtowncoder.jsonperf.dzone.MeasurementType.EXCEPTION; break;
							case 943038113: _type_ = com.cowtowncoder.jsonperf.dzone.MeasurementType.METHOD_CALL; break;
							default:
								throw new java.io.IOException("Unknown enum value: '" + reader.getLastName() + "' at position " + reader.positionInStream());
						}
						nextToken = reader.getNextToken();
					} else throw new java.io.IOException("Expecting '\"' at position " + reader.positionInStream() + ". Found " + (char)nextToken);
						break;
					case 1605909710:
						_measurementId_ = com.dslplatform.json.StringConverter.deserialize(reader);
					nextToken = reader.getNextToken();
						break;
					default:
						nextToken = reader.skip();
						break;
				}
			}
			while (nextToken == ',') {
				nextToken = reader.getNextToken();
				nameHash = reader.fillName();
				nextToken = reader.getNextToken();
				if(nextToken == 'n') {
					if (reader.wasNull()) {
						nextToken = reader.getNextToken();
						continue;
					} else {
						throw new java.io.IOException("Expecting 'u' (as null) at position " + reader.positionInStream() + ". Found " + (char)nextToken);
					}
				}
				switch(nameHash) {
					
					case 799079693:
						_duration_ = com.dslplatform.json.NumberConverter.deserializeLong(reader);
					nextToken = reader.getNextToken();
						break;
					case 1564253156:
						_time_ = com.dslplatform.json.NumberConverter.deserializeLong(reader);
					nextToken = reader.getNextToken();
						break;
					case 1361572173:
						
					if (nextToken == '"') {
						switch(reader.calcHash()) {
							case -678320345: _type_ = com.cowtowncoder.jsonperf.dzone.MeasurementType.WEB_REQUEST; break;
							case 18818719: _type_ = com.cowtowncoder.jsonperf.dzone.MeasurementType.SQL; break;
							case -602493290: _type_ = com.cowtowncoder.jsonperf.dzone.MeasurementType.EXCEPTION; break;
							case 943038113: _type_ = com.cowtowncoder.jsonperf.dzone.MeasurementType.METHOD_CALL; break;
							default:
								throw new java.io.IOException("Unknown enum value: '" + reader.getLastName() + "' at position " + reader.positionInStream());
						}
						nextToken = reader.getNextToken();
					} else throw new java.io.IOException("Expecting '\"' at position " + reader.positionInStream() + ". Found " + (char)nextToken);
						break;
					case 1605909710:
						_measurementId_ = com.dslplatform.json.StringConverter.deserialize(reader);
					nextToken = reader.getNextToken();
						break;
					default:
						nextToken = reader.skip();
						break;
				}
			}
			if (nextToken != '}') {
				throw new java.io.IOException("Expecting '}' at position " + reader.positionInStream() + ". Found " + (char)nextToken);
			}
		}
		
		instance.setDuration(_duration_);
		instance.setTime(_time_);
		instance.setType(_type_);
		instance.setMeasurementId(_measurementId_);
	}
	
	public static void serialize(final com.cowtowncoder.jsonperf.dzone.MeasurementPOJO self, final com.dslplatform.json.JsonWriter sw, final boolean minimal) {
		sw.writeByte(com.dslplatform.json.JsonWriter.OBJECT_START);
		if (minimal) {
			__serializeJsonObjectMinimal(self, sw, false);
		} else {
			__serializeJsonObjectFull(self, sw, false);
		}
		sw.writeByte(com.dslplatform.json.JsonWriter.OBJECT_END);
	}

	static void __serializeJsonObjectMinimal(final com.cowtowncoder.jsonperf.dzone.MeasurementPOJO self, com.dslplatform.json.JsonWriter sw, boolean hasWrittenProperty) {
		
		
		final java.util.List<com.cowtowncoder.jsonperf.dzone.MeasurementRecord> _tmp_items_ = self.getItems();
		if(_tmp_items_ != null && self.getItems().size() != 0) {
			hasWrittenProperty = true;
			sw.writeAscii("\"items\":[", 9);
			com.cowtowncoder.jsonperf.dzone.MeasurementRecord item = _tmp_items_.get(0);
				if(item != null)  {
					sw.writeByte(com.dslplatform.json.JsonWriter.OBJECT_START);
					dsl_json.json.ExternalSerialization.__serializeJsonObjectMinimal(item, sw, false); 
					sw.writeByte(com.dslplatform.json.JsonWriter.OBJECT_END);
				} else sw.writeNull();
			for(int i = 1; i < _tmp_items_.size(); i++) {
				sw.writeByte(com.dslplatform.json.JsonWriter.COMMA);	
				item = _tmp_items_.get(i);
				if(item != null)  {
					sw.writeByte(com.dslplatform.json.JsonWriter.OBJECT_START);
					dsl_json.json.ExternalSerialization.__serializeJsonObjectMinimal(item, sw, false); 
					sw.writeByte(com.dslplatform.json.JsonWriter.OBJECT_END);
				} else sw.writeNull();
			}
			sw.writeByte(com.dslplatform.json.JsonWriter.ARRAY_END);
		}
		else if(self.getItems() != null) {
			if(hasWrittenProperty) sw.writeByte(com.dslplatform.json.JsonWriter.COMMA);
			hasWrittenProperty = true;
			sw.writeAscii("\"items\":[]", 10);
		}
	}

	static void __serializeJsonObjectFull(final com.cowtowncoder.jsonperf.dzone.MeasurementPOJO self, com.dslplatform.json.JsonWriter sw, boolean hasWrittenProperty) {
		
		
		final java.util.List<com.cowtowncoder.jsonperf.dzone.MeasurementRecord> _tmp_items_ = self.getItems();
		if(_tmp_items_ != null && self.getItems().size() != 0) {
			sw.writeAscii("\"items\":[", 9);
			com.cowtowncoder.jsonperf.dzone.MeasurementRecord item = _tmp_items_.get(0);
				if(item != null)  {
					sw.writeByte(com.dslplatform.json.JsonWriter.OBJECT_START);
					dsl_json.json.ExternalSerialization.__serializeJsonObjectFull(item, sw, false); 
					sw.writeByte(com.dslplatform.json.JsonWriter.OBJECT_END);
				} else sw.writeNull();
			for(int i = 1; i < _tmp_items_.size(); i++) {
				sw.writeByte(com.dslplatform.json.JsonWriter.COMMA);	
				item = _tmp_items_.get(i);
				if(item != null)  {
					sw.writeByte(com.dslplatform.json.JsonWriter.OBJECT_START);
					dsl_json.json.ExternalSerialization.__serializeJsonObjectFull(item, sw, false); 
					sw.writeByte(com.dslplatform.json.JsonWriter.OBJECT_END);
				} else sw.writeNull();
			}
			sw.writeByte(com.dslplatform.json.JsonWriter.ARRAY_END);
		}
		else if(self.getItems() != null) sw.writeAscii("\"items\":[]", 10);
		else sw.writeAscii("\"items\":null", 12);
	}

	public static final com.dslplatform.json.JsonReader.ReadObject<com.cowtowncoder.jsonperf.dzone.MeasurementPOJO> JSON_READER_struct0 = new com.dslplatform.json.JsonReader.ReadObject<com.cowtowncoder.jsonperf.dzone.MeasurementPOJO>() {
		@SuppressWarnings("unchecked")
		@Override
		public com.cowtowncoder.jsonperf.dzone.MeasurementPOJO read(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
			if(reader.last() != '{') {
				throw new java.io.IOException("Expecting \'{\' at position " + reader.positionInStream() + ". Found " + (char)reader.last());
			}
			reader.getNextToken();
			final com.cowtowncoder.jsonperf.dzone.MeasurementPOJO instance = new com.cowtowncoder.jsonperf.dzone.MeasurementPOJO();
			deserialize(instance, reader);
			return instance;
		}
	};

	@SuppressWarnings("unchecked")
	static com.cowtowncoder.jsonperf.dzone.MeasurementPOJO deserializestruct0(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
		final com.cowtowncoder.jsonperf.dzone.MeasurementPOJO instance = new com.cowtowncoder.jsonperf.dzone.MeasurementPOJO();
		deserialize(instance, reader);
		return instance;
	}

	@SuppressWarnings("unchecked")
	static void deserialize(final com.cowtowncoder.jsonperf.dzone.MeasurementPOJO instance, final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
		
		java.util.List<com.cowtowncoder.jsonperf.dzone.MeasurementRecord> _items_ = null;
		byte nextToken = reader.last();
		if(nextToken != '}') {
			int nameHash = reader.fillName();
			nextToken = reader.getNextToken();
			if(nextToken == 'n') {
				if (reader.wasNull()) {
					nextToken = reader.getNextToken();
				} else {
					throw new java.io.IOException("Expecting 'u' (as null) at position " + reader.positionInStream() + ". Found " + (char)nextToken);
				}
			} else {
				switch(nameHash) {
					
					case 981021583:
						
					if (nextToken == '[') {
						nextToken = reader.getNextToken();
						if (nextToken == ']') {
							_items_ = new java.util.ArrayList<com.cowtowncoder.jsonperf.dzone.MeasurementRecord>(4);
						} else {
							java.util.List<com.cowtowncoder.jsonperf.dzone.MeasurementRecord> __res = reader.deserializeNullableCollection(JSON_READER_struct1);
							_items_ = __res;
						}
						nextToken = reader.getNextToken();
					} else throw new java.io.IOException("Expecting '[' at position " + reader.positionInStream() + ". Found " + (char)nextToken);
						break;
					default:
						nextToken = reader.skip();
						break;
				}
			}
			while (nextToken == ',') {
				nextToken = reader.getNextToken();
				nameHash = reader.fillName();
				nextToken = reader.getNextToken();
				if(nextToken == 'n') {
					if (reader.wasNull()) {
						nextToken = reader.getNextToken();
						continue;
					} else {
						throw new java.io.IOException("Expecting 'u' (as null) at position " + reader.positionInStream() + ". Found " + (char)nextToken);
					}
				}
				switch(nameHash) {
					
					case 981021583:
						
					if (nextToken == '[') {
						nextToken = reader.getNextToken();
						if (nextToken == ']') {
							_items_ = new java.util.ArrayList<com.cowtowncoder.jsonperf.dzone.MeasurementRecord>(4);
						} else {
							java.util.List<com.cowtowncoder.jsonperf.dzone.MeasurementRecord> __res = reader.deserializeNullableCollection(JSON_READER_struct1);
							_items_ = __res;
						}
						nextToken = reader.getNextToken();
					} else throw new java.io.IOException("Expecting '[' at position " + reader.positionInStream() + ". Found " + (char)nextToken);
						break;
					default:
						nextToken = reader.skip();
						break;
				}
			}
			if (nextToken != '}') {
				throw new java.io.IOException("Expecting '}' at position " + reader.positionInStream() + ". Found " + (char)nextToken);
			}
		}
		
		instance.setItems(_items_);
	}
}
