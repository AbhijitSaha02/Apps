// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';

import 'basic_widgets.dart';
import 'home.dart';
import 'input.dart';
import 'layout.dart';
import 'layout_widgets/layout_column.dart';
import 'layout_widgets/layout_container.dart';
import 'layout_widgets/layout_gridview.dart';
import 'layout_widgets/layout_listview.dart';
import 'layout_widgets/layout_row.dart';
import 'layout_widgets/layout_stack.dart';
import 'navig.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(fontFamily: 'Oxygen'),
      initialRoute: '/',
      routes: {
        '/': (context) => MyHomePage(),
        '/navigation': (context) => NavigationScreen(),
        '/layout': (context) => LayoutWidgets(),
        '/input': (context) => InputWidget(),
        '/basic': (context) => BasicWidgets(),
        '/layout/container': (context) => LayoutContainer(),
        '/layout/gridview': (context) => LayoutGridview(),
        '/layout/listview': (context) => LayoutListview(),
        '/layout/row': (context) => LayoutRow(),
        '/layout/column': (context) => LayoutColumn(),
        '/layout/stack': (context) => LayoutStack(),
      },
    );
  }
}
