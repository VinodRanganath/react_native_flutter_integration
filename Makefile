populate-flutter-module:
	mkdir -p flutter && cp -r ../../flutterflow/hello_world_module/ ./flutter

updade-flutter-module:
	cp -r ./flutter/build/host/outputs/repo ./localmaven