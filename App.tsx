/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from 'react';
import {Pressable, SafeAreaView, StyleSheet, Text, View} from 'react-native';
import NativeFlutterModule from './turboModuleSpecs/NativeFlutterModule';

const styles = StyleSheet.create({
  viewport: {
    width: '100%',
    height: '100%',
    backgroundColor: '#444',
  },
  container: {
    width: '100%',
    height: '100%',
    padding: 16,
    justifyContent: 'center',
  },
  button: {
    borderRadius: 5,
    backgroundColor: '#2ec4b6',
    width: 200,
    height: 50,
    padding: 16,
    alignSelf: 'center',
  },
  text: {
    color: '#444',
    textAlign: 'center',
    fontWeight: '600',
  },
});

const App: React.FunctionComponent = () => {
  const {launch} = NativeFlutterModule;

  const openFlutterModule = () => {
    try {
      launch('Flutter Integration', value =>
        console.log('flutter_integration: openFlutterModule: flutter module launched with value:', value),
      );
    } catch (e: any) {
      console.log('flutter_integration: openFlutterModule: failed with error:', e.message);
    }
  };

  return (
    <SafeAreaView>
      <View style={styles.viewport}>
        <View style={styles.container}>
          <Pressable style={styles.button} onPress={openFlutterModule}>
            <Text style={styles.text}>Open Flutter Module</Text>
          </Pressable>
        </View>
      </View>
    </SafeAreaView>
  );
};

export default App;
