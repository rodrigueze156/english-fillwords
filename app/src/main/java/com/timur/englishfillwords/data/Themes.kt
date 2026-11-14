package com.timur.englishfillwords.data

/** Одно английское слово и его перевод на русский. */
data class Word(val en: String, val ru: String)

/** Тематический набор слов одного уровня. */
data class Theme(
    val id: String,
    val nameRu: String,
    val nameEn: String,
    val emoji: String,
    val words: List<Word>
)

object Themes {

    val trees = Theme(
        id = "trees",
        nameRu = "Деревья и кусты",
        nameEn = "Trees & Shrubs",
        emoji = "🌳",
        words = listOf(
            Word("OAK", "дуб"),
            Word("MAPLE", "клён"),
            Word("BIRCH", "берёза"),
            Word("PINE", "сосна"),
            Word("WILLOW", "ива"),
            Word("CEDAR", "кедр"),
            Word("ELM", "вяз"),
            Word("POPLAR", "тополь"),
            Word("HAZEL", "орешник"),
            Word("SPRUCE", "ель"),
            Word("ALDER", "ольха"),
            Word("CHERRY", "черешня"),
            Word("WALNUT", "грецкий орех"),
            Word("ASH", "ясень"),
            Word("CYPRESS", "кипарис"),
            Word("SYCAMORE", "платан")
        )
    )

    val animals = Theme(
        id = "animals",
        nameRu = "Животные",
        nameEn = "Animals",
        emoji = "🦊",
        words = listOf(
            Word("TIGER", "тигр"),
            Word("EAGLE", "орёл"),
            Word("RABBIT", "кролик"),
            Word("DOLPHIN", "дельфин"),
            Word("PANDA", "панда"),
            Word("WOLF", "волк"),
            Word("OTTER", "выдра"),
            Word("LYNX", "рысь"),
            Word("HORSE", "лошадь"),
            Word("MONKEY", "обезьяна"),
            Word("SNAKE", "змея"),
            Word("ZEBRA", "зебра"),
            Word("BEAR", "медведь"),
            Word("FOX", "лиса"),
            Word("KOALA", "коала"),
            Word("LEOPARD", "леопард")
        )
    )

    val food = Theme(
        id = "food",
        nameRu = "Еда и напитки",
        nameEn = "Food & Drinks",
        emoji = "🍎",
        words = listOf(
            Word("BREAD", "хлеб"),
            Word("CHEESE", "сыр"),
            Word("APPLE", "яблоко"),
            Word("HONEY", "мёд"),
            Word("PEPPER", "перец"),
            Word("BUTTER", "масло"),
            Word("SUGAR", "сахар"),
            Word("ORANGE", "апельсин"),
            Word("LEMON", "лимон"),
            Word("RICE", "рис"),
            Word("SOUP", "суп"),
            Word("SALAD", "салат"),
            Word("COFFEE", "кофе"),
            Word("TOMATO", "томат"),
            Word("GARLIC", "чеснок"),
            Word("YOGURT", "йогурт")
        )
    )

    val travel = Theme(
        id = "travel",
        nameRu = "Путешествия",
        nameEn = "Travel",
        emoji = "🧭",
        words = listOf(
            Word("PASSPORT", "паспорт"),
            Word("TICKET", "билет"),
            Word("HOTEL", "отель"),
            Word("BEACH", "пляж"),
            Word("SUITCASE", "чемодан"),
            Word("ISLAND", "остров"),
            Word("CAMERA", "камера"),
            Word("TRAIN", "поезд"),
            Word("PLANE", "самолёт"),
            Word("BRIDGE", "мост"),
            Word("MARKET", "рынок"),
            Word("HARBOR", "гавань"),
            Word("GUIDE", "гид"),
            Word("LUGGAGE", "багаж"),
            Word("RESORT", "курорт"),
            Word("VOYAGE", "путешествие")
        )
    )

    val space = Theme(
        id = "space",
        nameRu = "Космос",
        nameEn = "Space",
        emoji = "🚀",
        words = listOf(
            Word("PLANET", "планета"),
            Word("COMET", "комета"),
            Word("GALAXY", "галактика"),
            Word("ROCKET", "ракета"),
            Word("ORBIT", "орбита"),
            Word("MOON", "луна"),
            Word("STAR", "звезда"),
            Word("NEBULA", "туманность"),
            Word("SATURN", "Сатурн"),
            Word("METEOR", "метеор"),
            Word("COSMOS", "космос"),
            Word("VENUS", "Венера"),
            Word("MARS", "Марс"),
            Word("EARTH", "Земля"),
            Word("SOLAR", "солнечный"),
            Word("CRATER", "кратер")
        )
    )

    val music = Theme(
        id = "music",
        nameRu = "Музыка",
        nameEn = "Music",
        emoji = "🎵",
        words = listOf(
            Word("GUITAR", "гитара"),
            Word("PIANO", "пианино"),
            Word("VIOLIN", "скрипка"),
            Word("DRUM", "барабан"),
            Word("MELODY", "мелодия"),
            Word("CHORD", "аккорд"),
            Word("OPERA", "опера"),
            Word("RHYTHM", "ритм"),
            Word("TRUMPET", "труба"),
            Word("CELLO", "виолончель"),
            Word("SONG", "песня"),
            Word("STAGE", "сцена"),
            Word("BAND", "группа"),
            Word("JAZZ", "джаз"),
            Word("NOTE", "нота"),
            Word("CHOIR", "хор")
        )
    )

    val ocean = Theme(
        id = "ocean",
        nameRu = "Океан",
        nameEn = "Ocean",
        emoji = "🐬",
        words = listOf(
            Word("CORAL", "коралл"),
            Word("SHARK", "акула"),
            Word("WHALE", "кит"),
            Word("WAVE", "волна"),
            Word("SHELL", "ракушка"),
            Word("DOLPHIN", "дельфин"),
            Word("OCTOPUS", "осьминог"),
            Word("TURTLE", "черепаха"),
            Word("ISLAND", "остров"),
            Word("CURRENT", "течение"),
            Word("LAGOON", "лагуна"),
            Word("PEARL", "жемчуг"),
            Word("STINGRAY", "скат"),
            Word("SQUID", "кальмар"),
            Word("BEACH", "пляж"),
            Word("REEF", "риф")
        )
    )

    val flowers = Theme(
        id = "flowers",
        nameRu = "Цветы",
        nameEn = "Flowers",
        emoji = "🌸",
        words = listOf(
            Word("ROSE", "роза"),
            Word("TULIP", "тюльпан"),
            Word("DAISY", "маргаритка"),
            Word("LILAC", "сирень"),
            Word("ORCHID", "орхидея"),
            Word("LILY", "лилия"),
            Word("VIOLET", "фиалка"),
            Word("PEONY", "пион"),
            Word("JASMINE", "жасмин"),
            Word("LOTUS", "лотос"),
            Word("IRIS", "ирис"),
            Word("POPPY", "мак"),
            Word("FERN", "папоротник"),
            Word("CACTUS", "кактус"),
            Word("MAGNOLIA", "магнолия"),
            Word("BEGONIA", "бегония")
        )
    )

    val weather = Theme(
        id = "weather",
        nameRu = "Погода",
        nameEn = "Weather",
        emoji = "⛅",
        words = listOf(
            Word("SUN", "солнце"),
            Word("RAIN", "дождь"),
            Word("SNOW", "снег"),
            Word("WIND", "ветер"),
            Word("STORM", "буря"),
            Word("CLOUD", "облако"),
            Word("FOG", "туман"),
            Word("FROST", "мороз"),
            Word("THUNDER", "гром"),
            Word("LIGHTNING", "молния"),
            Word("BREEZE", "бриз"),
            Word("HAIL", "град"),
            Word("HEAT", "жара"),
            Word("ICE", "лёд"),
            Word("DRIZZLE", "морось"),
            Word("RAINBOW", "радуга")
        )
    )

    val clothes = Theme(
        id = "clothes",
        nameRu = "Одежда",
        nameEn = "Clothes",
        emoji = "👕",
        words = listOf(
            Word("SHIRT", "рубашка"),
            Word("DRESS", "платье"),
            Word("JEANS", "джинсы"),
            Word("COAT", "пальто"),
            Word("SOCKS", "носки"),
            Word("BOOTS", "ботинки"),
            Word("SCARF", "шарф"),
            Word("GLOVES", "перчатки"),
            Word("HAT", "шляпа"),
            Word("SKIRT", "юбка"),
            Word("JACKET", "куртка"),
            Word("SWEATER", "свитер"),
            Word("TIE", "галстук"),
            Word("BELT", "ремень"),
            Word("SHORTS", "шорты"),
            Word("UNIFORM", "форма")
        )
    )

    val body = Theme(
        id = "body",
        nameRu = "Тело",
        nameEn = "Body",
        emoji = "🫀",
        words = listOf(
            Word("HEAD", "голова"),
            Word("HAND", "кисть"),
            Word("FOOT", "ступня"),
            Word("EYE", "глаз"),
            Word("EAR", "ухо"),
            Word("NOSE", "нос"),
            Word("MOUTH", "рот"),
            Word("HAIR", "волосы"),
            Word("ARM", "рука"),
            Word("LEG", "нога"),
            Word("FINGER", "палец"),
            Word("SHOULDER", "плечо"),
            Word("KNEE", "колено"),
            Word("BACK", "спина"),
            Word("NECK", "шея"),
            Word("HEART", "сердце")
        )
    )

    val house = Theme(
        id = "house",
        nameRu = "Дом",
        nameEn = "House",
        emoji = "🏠",
        words = listOf(
            Word("ROOF", "крыша"),
            Word("WALL", "стена"),
            Word("DOOR", "дверь"),
            Word("WINDOW", "окно"),
            Word("FLOOR", "пол"),
            Word("KITCHEN", "кухня"),
            Word("BEDROOM", "спальня"),
            Word("CHAIR", "стул"),
            Word("TABLE", "стол"),
            Word("SOFA", "диван"),
            Word("LAMP", "лампа"),
            Word("MIRROR", "зеркало"),
            Word("STAIRS", "лестница"),
            Word("GARDEN", "сад"),
            Word("GARAGE", "гараж"),
            Word("CHIMNEY", "труба")
        )
    )

    val school = Theme(
        id = "school",
        nameRu = "Школа",
        nameEn = "School",
        emoji = "🎒",
        words = listOf(
            Word("DESK", "парта"),
            Word("BOOK", "книга"),
            Word("PEN", "ручка"),
            Word("PENCIL", "карандаш"),
            Word("RULER", "линейка"),
            Word("CLASS", "класс"),
            Word("LESSON", "урок"),
            Word("TEACHER", "учитель"),
            Word("STUDENT", "ученик"),
            Word("BOARD", "доска"),
            Word("NOTEBOOK", "тетрадь"),
            Word("SUBJECT", "предмет"),
            Word("EXAM", "экзамен"),
            Word("GRADE", "оценка"),
            Word("BREAK", "перемена"),
            Word("HOMEWORK", "домашнее задание")
        )
    )

    val sport = Theme(
        id = "sport",
        nameRu = "Спорт",
        nameEn = "Sport",
        emoji = "⚽",
        words = listOf(
            Word("BALL", "мяч"),
            Word("GOAL", "гол"),
            Word("TEAM", "команда"),
            Word("COACH", "тренер"),
            Word("MATCH", "матч"),
            Word("SCORE", "счёт"),
            Word("RACE", "гонка"),
            Word("JUMP", "прыжок"),
            Word("SWIM", "плавание"),
            Word("RUN", "бег"),
            Word("TENNIS", "теннис"),
            Word("FOOTBALL", "футбол"),
            Word("HOCKEY", "хоккей"),
            Word("MEDAL", "медаль"),
            Word("TROPHY", "трофей"),
            Word("STADIUM", "стадион")
        )
    )

    val transport = Theme(
        id = "transport",
        nameRu = "Транспорт",
        nameEn = "Transport",
        emoji = "🚌",
        words = listOf(
            Word("CAR", "машина"),
            Word("BUS", "автобус"),
            Word("TRAIN", "поезд"),
            Word("PLANE", "самолёт"),
            Word("SHIP", "корабль"),
            Word("BIKE", "велосипед"),
            Word("TRUCK", "грузовик"),
            Word("TAXI", "такси"),
            Word("BOAT", "лодка"),
            Word("SCOOTER", "самокат"),
            Word("SUBWAY", "метро"),
            Word("TRAM", "трамвай"),
            Word("FERRY", "паром"),
            Word("VAN", "фургон"),
            Word("JET", "реактивный самолёт"),
            Word("WAGON", "вагон")
        )
    )

    val insects = Theme(
        id = "insects",
        nameRu = "Насекомые",
        nameEn = "Insects",
        emoji = "🐝",
        words = listOf(
            Word("ANT", "муравей"),
            Word("BEE", "пчела"),
            Word("WASP", "оса"),
            Word("FLY", "муха"),
            Word("MOTH", "мотылёк"),
            Word("BEETLE", "жук"),
            Word("SPIDER", "паук"),
            Word("BUTTERFLY", "бабочка"),
            Word("DRAGONFLY", "стрекоза"),
            Word("LADYBUG", "божья коровка"),
            Word("CRICKET", "сверчок"),
            Word("GRASSHOPPER", "кузнечик"),
            Word("MOSQUITO", "комар"),
            Word("CICADA", "цикада"),
            Word("TERMITE", "термит"),
            Word("CATERPILLAR", "гусеница")
        )
    )

    val desserts = Theme(
        id = "desserts",
        nameRu = "Сладости",
        nameEn = "Desserts",
        emoji = "🍰",
        words = listOf(
            Word("CAKE", "торт"),
            Word("PIE", "пирог"),
            Word("COOKIE", "печенье"),
            Word("CANDY", "конфета"),
            Word("HONEY", "мёд"),
            Word("SUGAR", "сахар"),
            Word("CHOCOLATE", "шоколад"),
            Word("DONUT", "пончик"),
            Word("MUFFIN", "маффин"),
            Word("WAFFLE", "вафля"),
            Word("PANCAKE", "блин"),
            Word("PUDDING", "пудинг"),
            Word("JELLY", "желе"),
            Word("CARAMEL", "карамель"),
            Word("BISCUIT", "бисквит"),
            Word("MARSHMALLOW", "зефир")
        )
    )

    val professions = Theme(
        id = "professions",
        nameRu = "Профессии",
        nameEn = "Professions",
        emoji = "👷",
        words = listOf(
            Word("DOCTOR", "врач"),
            Word("NURSE", "медсестра"),
            Word("PROFESSOR", "профессор"),
            Word("PILOT", "пилот"),
            Word("CHEF", "повар"),
            Word("DRIVER", "водитель"),
            Word("ARTIST", "художник"),
            Word("WRITER", "писатель"),
            Word("SINGER", "певец"),
            Word("ACTOR", "актёр"),
            Word("LAWYER", "юрист"),
            Word("FARMER", "фермер"),
            Word("ENGINEER", "инженер"),
            Word("PAINTER", "маляр"),
            Word("GUARD", "охранник"),
            Word("BAKER", "пекарь")
        )
    )

    val fruits = Theme(
        id = "fruits",
        nameRu = "Фрукты",
        nameEn = "Fruits",
        emoji = "🍎",
        words = listOf(
            Word("APPLE", "яблоко"),
            Word("BANANA", "банан"),
            Word("ORANGE", "апельсин"),
            Word("GRAPE", "виноград"),
            Word("PEACH", "персик"),
            Word("PEAR", "груша"),
            Word("PLUM", "слива"),
            Word("CHERRY", "черешня"),
            Word("LEMON", "лимон"),
            Word("MANGO", "манго"),
            Word("MELON", "дыня"),
            Word("KIWI", "киви"),
            Word("POMEGRANATE", "гранат"),
            Word("APRICOT", "абрикос"),
            Word("COCONUT", "кокос"),
            Word("PINEAPPLE", "ананас")
        )
    )

    val vegetables = Theme(
        id = "vegetables",
        nameRu = "Овощи",
        nameEn = "Vegetables",
        emoji = "🥕",
        words = listOf(
            Word("CARROT", "морковь"),
            Word("POTATO", "картофель"),
            Word("TOMATO", "помидор"),
            Word("ONION", "лук"),
            Word("GARLIC", "чеснок"),
            Word("CUCUMBER", "огурец"),
            Word("PEPPER", "перец"),
            Word("CABBAGE", "капуста"),
            Word("PUMPKIN", "тыква"),
            Word("BEET", "свёкла"),
            Word("RADISH", "редис"),
            Word("LETTUCE", "салат"),
            Word("SPINACH", "шпинат"),
            Word("BROCCOLI", "брокколи"),
            Word("CORN", "кукуруза"),
            Word("EGGPLANT", "баклажан")
        )
    )

    val colors = Theme(
        id = "colors",
        nameRu = "Цвета",
        nameEn = "Colors",
        emoji = "🎨",
        words = listOf(
            Word("RED", "красный"),
            Word("BLUE", "синий"),
            Word("GREEN", "зелёный"),
            Word("YELLOW", "жёлтый"),
            Word("BLACK", "чёрный"),
            Word("WHITE", "белый"),
            Word("ORANGE", "оранжевый"),
            Word("PURPLE", "фиолетовый"),
            Word("PINK", "розовый"),
            Word("BROWN", "коричневый"),
            Word("GREY", "серый"),
            Word("GOLD", "золотой"),
            Word("SILVER", "серебряный"),
            Word("BEIGE", "бежевый"),
            Word("VIOLET", "сиреневый"),
            Word("TURQUOISE", "бирюзовый")
        )
    )

    val shapes = Theme(
        id = "shapes",
        nameRu = "Формы",
        nameEn = "Shapes",
        emoji = "🔷",
        words = listOf(
            Word("CIRCLE", "круг"),
            Word("SQUARE", "квадрат"),
            Word("TRIANGLE", "треугольник"),
            Word("RECTANGLE", "прямоугольник"),
            Word("OVAL", "овал"),
            Word("STAR", "звезда"),
            Word("HEART", "сердце"),
            Word("DIAMOND", "ромб"),
            Word("CUBE", "куб"),
            Word("SPHERE", "сфера"),
            Word("CONE", "конус"),
            Word("CYLINDER", "цилиндр"),
            Word("PYRAMID", "пирамида"),
            Word("LINE", "линия"),
            Word("CROSS", "крест"),
            Word("RING", "кольцо")
        )
    )

    val kitchen = Theme(
        id = "kitchen",
        nameRu = "Кухня",
        nameEn = "Kitchen",
        emoji = "🍳",
        words = listOf(
            Word("PLATE", "тарелка"),
            Word("BOWL", "миска"),
            Word("CUP", "чашка"),
            Word("MUG", "кружка"),
            Word("SPOON", "ложка"),
            Word("FORK", "вилка"),
            Word("KNIFE", "нож"),
            Word("PAN", "сковорода"),
            Word("POT", "кастрюля"),
            Word("KETTLE", "чайник"),
            Word("OVEN", "духовка"),
            Word("FRIDGE", "холодильник"),
            Word("SINK", "раковина"),
            Word("TOWEL", "полотенце"),
            Word("TEAPOT", "заварник"),
            Word("GRATER", "тёрка")
        )
    )

    val tools = Theme(
        id = "tools",
        nameRu = "Инструменты",
        nameEn = "Tools",
        emoji = "🔧",
        words = listOf(
            Word("HAMMER", "молоток"),
            Word("NAIL", "гвоздь"),
            Word("SCREW", "винт"),
            Word("WRENCH", "гаечный ключ"),
            Word("PLIERS", "плоскогубцы"),
            Word("SAW", "пила"),
            Word("DRILL", "дрель"),
            Word("FILE", "напильник"),
            Word("LEVEL", "уровень"),
            Word("TAPE", "лента"),
            Word("CHISEL", "стамеска"),
            Word("CLAMP", "струбцина"),
            Word("RULER", "линейка"),
            Word("LADDER", "стремянка"),
            Word("AXE", "топор"),
            Word("SHOVEL", "лопата")
        )
    )

    val birds = Theme(
        id = "birds",
        nameRu = "Птицы",
        nameEn = "Birds",
        emoji = "🐦",
        words = listOf(
            Word("EAGLE", "орёл"),
            Word("SPARROW", "воробей"),
            Word("ROBIN", "малиновка"),
            Word("DOVE", "голубь"),
            Word("OWL", "сова"),
            Word("CROW", "ворона"),
            Word("DUCK", "утка"),
            Word("SWAN", "лебедь"),
            Word("GOOSE", "гусь"),
            Word("TURKEY", "индюк"),
            Word("PARROT", "попугай"),
            Word("FALCON", "сокол"),
            Word("HERON", "цапля"),
            Word("STORK", "аист"),
            Word("PENGUIN", "пингвин"),
            Word("OSTRICH", "страус")
        )
    )

    val fish = Theme(
        id = "fish",
        nameRu = "Рыбы",
        nameEn = "Fish",
        emoji = "🐟",
        words = listOf(
            Word("SALMON", "лосось"),
            Word("TROUT", "форель"),
            Word("SHARK", "акула"),
            Word("TUNA", "тунец"),
            Word("COD", "треска"),
            Word("HERRING", "сельдь"),
            Word("CARP", "карп"),
            Word("EEL", "угорь"),
            Word("RAY", "скат"),
            Word("BASS", "окунь"),
            Word("PIKE", "щука"),
            Word("MACKEREL", "макрель"),
            Word("SARDINE", "сардина"),
            Word("ANCHOVY", "анчоус"),
            Word("CATFISH", "сом"),
            Word("GOLDFISH", "золотая рыбка")
        )
    )

    val toys = Theme(
        id = "toys",
        nameRu = "Игрушки",
        nameEn = "Toys",
        emoji = "🧸",
        words = listOf(
            Word("DOLL", "кукла"),
            Word("BALL", "мяч"),
            Word("BLOCKS", "кубики"),
            Word("PUZZLE", "пазл"),
            Word("TEDDY", "мишка"),
            Word("KITE", "воздушный змей"),
            Word("TRAIN", "поезд"),
            Word("ROBOT", "робот"),
            Word("DRUM", "барабан"),
            Word("YOYO", "йо-йо"),
            Word("TOP", "волчок"),
            Word("RATTLE", "погремушка"),
            Word("MARBLES", "шарики"),
            Word("CHESS", "шахматы"),
            Word("LEGO", "конструктор"),
            Word("WHISTLE", "свисток")
        )
    )

    val feelings = Theme(
        id = "feelings",
        nameRu = "Чувства",
        nameEn = "Feelings",
        emoji = "😊",
        words = listOf(
            Word("HAPPY", "радость"),
            Word("SAD", "грусть"),
            Word("ANGRY", "злость"),
            Word("JOY", "веселье"),
            Word("FEAR", "страх"),
            Word("LOVE", "любовь"),
            Word("HOPE", "надежда"),
            Word("CALM", "спокойствие"),
            Word("PROUD", "гордость"),
            Word("SHY", "стеснение"),
            Word("BORED", "скука"),
            Word("EXCITED", "восторг"),
            Word("NERVOUS", "волнение"),
            Word("JEALOUS", "зависть"),
            Word("GRATEFUL", "благодарность"),
            Word("LONELY", "одиночество")
        )
    )

    val drinks = Theme(
        id = "drinks",
        nameRu = "Напитки",
        nameEn = "Drinks",
        emoji = "☕",
        words = listOf(
            Word("WATER", "вода"),
            Word("TEA", "чай"),
            Word("COFFEE", "кофе"),
            Word("JUICE", "сок"),
            Word("MILK", "молоко"),
            Word("COCOA", "какао"),
            Word("LEMONADE", "лимонад"),
            Word("COLA", "кола"),
            Word("SODA", "газировка"),
            Word("WINE", "вино"),
            Word("BEER", "пиво"),
            Word("CIDER", "сидр"),
            Word("COCKTAIL", "коктейль"),
            Word("SMOOTHIE", "смузи"),
            Word("NECTAR", "нектар"),
            Word("SYRUP", "сироп")
        )
    )

    val bakery = Theme(
        id = "bakery",
        nameRu = "Выпечка",
        nameEn = "Bakery",
        emoji = "🥐",
        words = listOf(
            Word("BREAD", "хлеб"),
            Word("BUN", "булочка"),
            Word("ROLL", "рулет"),
            Word("LOAF", "буханка"),
            Word("BAGUETTE", "багет"),
            Word("CROISSANT", "круассан"),
            Word("PRETZEL", "крендель"),
            Word("BAGEL", "бублик"),
            Word("TOAST", "тост"),
            Word("DOUGH", "тесто"),
            Word("FLOUR", "мука"),
            Word("YEAST", "дрожжи"),
            Word("GLAZE", "глазурь"),
            Word("CRUMB", "крошка"),
            Word("BISCUIT", "печенье"),
            Word("SCONE", "скоун")
        )
    )

    val furniture = Theme(
        id = "furniture",
        nameRu = "Мебель",
        nameEn = "Furniture",
        emoji = "🪑",
        words = listOf(
            Word("WARDROBE", "шкаф"),
            Word("DRESSER", "комод"),
            Word("CABINET", "сервант"),
            Word("SHELF", "полка"),
            Word("STOOL", "табурет"),
            Word("BENCH", "скамейка"),
            Word("BED", "кровать"),
            Word("COUCH", "кушетка"),
            Word("ARMCHAIR", "кресло"),
            Word("DRAWER", "ящик"),
            Word("OTTOMAN", "пуф"),
            Word("CRADLE", "колыбель"),
            Word("MATTRESS", "матрас"),
            Word("RACK", "стеллаж"),
            Word("CHEST", "сундук"),
            Word("VANITY", "туалетный столик")
        )
    )

    val bathroom = Theme(
        id = "bathroom",
        nameRu = "Ванная",
        nameEn = "Bathroom",
        emoji = "🛁",
        words = listOf(
            Word("SOAP", "мыло"),
            Word("SHAMPOO", "шампунь"),
            Word("COMB", "расчёска"),
            Word("BRUSH", "щётка"),
            Word("RAZOR", "бритва"),
            Word("TUB", "ванна"),
            Word("SHOWER", "душ"),
            Word("TOILET", "унитаз"),
            Word("SPONGE", "губка"),
            Word("LOTION", "лосьон"),
            Word("PASTE", "паста"),
            Word("FLOSS", "нить"),
            Word("BUCKET", "ведро"),
            Word("DRAIN", "слив"),
            Word("TAP", "кран"),
            Word("PLUNGER", "вантуз")
        )
    )

    val garden = Theme(
        id = "garden",
        nameRu = "Сад",
        nameEn = "Garden",
        emoji = "🌱",
        words = listOf(
            Word("SEED", "семя"),
            Word("SOIL", "почва"),
            Word("SPADE", "лопата"),
            Word("RAKE", "грабли"),
            Word("HOSE", "шланг"),
            Word("POT", "горшок"),
            Word("FENCE", "забор"),
            Word("WEED", "сорняк"),
            Word("MULCH", "мульча"),
            Word("BUD", "бутон"),
            Word("BLOOM", "цветение"),
            Word("TRELLIS", "решётка"),
            Word("GREENHOUSE", "теплица"),
            Word("COMPOST", "компост"),
            Word("ORCHARD", "фруктовый сад"),
            Word("SPROUT", "росток")
        )
    )

    val farm = Theme(
        id = "farm",
        nameRu = "Ферма",
        nameEn = "Farm",
        emoji = "🚜",
        words = listOf(
            Word("BARN", "амбар"),
            Word("FIELD", "поле"),
            Word("CROP", "урожай"),
            Word("PLOW", "плуг"),
            Word("TRACTOR", "трактор"),
            Word("HARVEST", "жатва"),
            Word("WHEAT", "пшеница"),
            Word("BARLEY", "ячмень"),
            Word("OAT", "овёс"),
            Word("HAY", "сено"),
            Word("STABLE", "конюшня"),
            Word("COOP", "курятник"),
            Word("SHEEP", "овца"),
            Word("GOAT", "коза"),
            Word("HORSE", "лошадь"),
            Word("COW", "корова")
        )
    )

    val mountains = Theme(
        id = "mountains",
        nameRu = "Горы",
        nameEn = "Mountains",
        emoji = "⛰️",
        words = listOf(
            Word("PEAK", "вершина"),
            Word("RIDGE", "хребет"),
            Word("SLOPE", "склон"),
            Word("CLIFF", "обрыв"),
            Word("VALLEY", "долина"),
            Word("GLACIER", "ледник"),
            Word("SUMMIT", "пик"),
            Word("ROCK", "скала"),
            Word("STONE", "камень"),
            Word("CAVE", "пещера"),
            Word("TRAIL", "тропа"),
            Word("CAMP", "лагерь"),
            Word("ROPE", "верёвка"),
            Word("AXE", "ледоруб"),
            Word("TENT", "палатка"),
            Word("ALTITUDE", "высота")
        )
    )

    val city = Theme(
        id = "city",
        nameRu = "Город",
        nameEn = "City",
        emoji = "🏙️",
        words = listOf(
            Word("STREET", "улица"),
            Word("PARK", "парк"),
            Word("BRIDGE", "мост"),
            Word("TOWER", "башня"),
            Word("MUSEUM", "музей"),
            Word("THEATER", "театр"),
            Word("BANK", "банк"),
            Word("SHOP", "магазин"),
            Word("CAFE", "кафе"),
            Word("MARKET", "рынок"),
            Word("HOTEL", "отель"),
            Word("OFFICE", "офис"),
            Word("SQUARE", "площадь"),
            Word("AVENUE", "проспект"),
            Word("FOUNTAIN", "фонтан"),
            Word("ALLEY", "переулок")
        )
    )

    val medicine = Theme(
        id = "medicine",
        nameRu = "Медицина",
        nameEn = "Medicine",
        emoji = "💊",
        words = listOf(
            Word("PILL", "таблетка"),
            Word("DOSE", "доза"),
            Word("VACCINE", "вакцина"),
            Word("SYRINGE", "шприц"),
            Word("BANDAGE", "бинт"),
            Word("PLASTER", "пластырь"),
            Word("CURE", "лекарство"),
            Word("CLINIC", "клиника"),
            Word("PATIENT", "пациент"),
            Word("WARD", "палата"),
            Word("SCALPEL", "скальпель"),
            Word("TABLET", "пилюля"),
            Word("DROPS", "капли"),
            Word("THERAPY", "терапия"),
            Word("CHECKUP", "осмотр"),
            Word("SURGERY", "операция")
        )
    )

    val technology = Theme(
        id = "technology",
        nameRu = "Технологии",
        nameEn = "Technology",
        emoji = "💻",
        words = listOf(
            Word("PHONE", "телефон"),
            Word("LAPTOP", "ноутбук"),
            Word("SCREEN", "экран"),
            Word("MOUSE", "мышь"),
            Word("KEYBOARD", "клавиатура"),
            Word("CHIP", "чип"),
            Word("CABLE", "кабель"),
            Word("DRONE", "дрон"),
            Word("CODE", "код"),
            Word("BYTE", "байт"),
            Word("SERVER", "сервер"),
            Word("ROUTER", "роутер"),
            Word("PIXEL", "пиксель"),
            Word("MODEM", "модем"),
            Word("GADGET", "гаджет"),
            Word("CONSOLE", "консоль")
        )
    )

    val brawlstars = Theme(
        id = "brawlstars",
        nameRu = "Бравл Старс",
        nameEn = "Brawl Stars",
        emoji = "🚭",
        words = listOf(
            Word("BRAWLER", "боец"),
            Word("GADGET", "гаджет"),
            Word("SKIN", "скин"),
            Word("POWERUP", "усиление"),
            Word("TROPHY", "трофей"),
            Word("GEM", "гем"),
            Word("BATTLE", "битва"),
            Word("ARENA", "арена"),
            Word("CLUB", "клуб"),
            Word("EVENT", "событие"),
            Word("STARPOINTS", "звёздные очки"),
            Word("SEASON", "сезон"),
            Word("REWARD", "награда"),
            Word("TICKET", "билет"),
            Word("BOSSFIGHT", "бой с боссом"),
            Word("DYNAMITE", "динамит")
        )
    )

    val myself = Theme(
        id = "myself",
        nameRu = "Родригез",
        nameEn = "Rodrigueze",
        emoji = "🫡",
        words = listOf(
            Word("RODRIGUEZ", "родригез"),
            Word("MYSELF", "я сам"),
            Word("HERO", "герой"),
            Word("LEGEND", "легенда"),
            Word("CHAMPION", "чемпион"),
            Word("VICTORY", "победа"),
            Word("COURAGE", "мужество"),
            Word("STRENGTH", "сила"),
            Word("HONOR", "честь"),
            Word("LOYALTY", "верность"),
            Word("RESPECT", "уважение"),
            Word("DREAM", "мечта"),
            Word("FREEDOM", "свобода"),
            Word("BROOKLI", "дарья"),
            Word("INSPIRATION", "вдохновение"),
            Word("PASSION", "страсть")
        )
    )
    
    val brawlers = Theme(
        id = "brawlers",
        nameRu = "Бравлеры",
        nameEn = "Brawlers",
        emoji = "🥊",
        words = listOf(
            Word("SHELLEY", "Шелли"),
            Word("NITA", "Нита"),
            Word("COLT", "Кольт"),
            Word("BULL", "Булл"),
            Word("JESSIE", "Джесси"),
            Word("BROCK", "Брок"),
            Word("DYNAMIKE", "Динамайк"),
            Word("TICK", "Тик"),
            Word("ELPRIMO", "Эль Примо"),
            Word("BARLEY", "Барли"),
            Word("PAM", "Пэм"),
            Word("TARA", "Тара"),
            Word("DARRYL", "Деррил"),
            Word("PIPER", "Пайпер"),
            Word("FRANK", "Фрэнк"),
            Word("GENE", "Джин")
        )
    )
    
    val brawlers2 = Theme(
        id = "brawlers2",
        nameRu = "Бравлеры 2",
        nameEn = "Brawlers 2",
        emoji = "🥋",
        words = listOf(
            Word("MAX", "Макс"),
            Word("MRP", "Мистер П"),
            Word("JACKY", "Джеки"),
            Word("BEA", "Би"),
            Word("SANDY", "Сэнди"),
            Word("EMZ", "Эмз"),
            Word("LOU", "Лу"),
            Word("COLETTE", "Колетт"),
            Word("BYRON", "Байрон"),
            Word("EDGAR", "Эдгар"),
            Word("GRIFF", "Грифф"),
            Word("JANET", "Джанет"),
            Word("BONNIE", "Бонни"),
            Word("OTIS", "Отис"),
            Word("STU", "Сту"),
            Word("GALE", "Гейл")
        )
    )

    val brawlers3 = Theme(
        id = "brawlers3",
        nameRu = "Бравлеры 3",
        nameEn = "Brawlers 3",
        emoji = "🏆",
        words = listOf(
            Word("COLETTE", "Колетт"),
            Word("BYRON", "Байрон"),
            Word("EDGAR", "Эдгар"),
            Word("GRIFF", "Грифф"),
            Word("JANET", "Джанет"),
            Word("BONNIE", "Бонни"),
            Word("OTIS", "Отис"),
            Word("STU", "Стю"),
            Word("BELLE", "Белль"),
            Word("SQUEAK", "Сквик"),
            Word("ASH", "Эш"),
            Word("ANGELO", "Анджело"),
            Word("RICO", "Рико"),
            Word("GROM", "Гром"),
            Word("FANG", "Фенг"),
            Word("EVE", "Ева")
        )
    )

    val localdarya = Theme(
        id = "localdarya",
        nameRu = "Локалки с Дашкой",
        nameEn = "Locals with Darya",
        emoji = "💞",
        words = listOf(
            Word("YAGODA", "ягода (ликер)"),
            Word("SLUTOV", "Шлюха Д.Г."),
            Word("FERTO", "лучшая песня"),
            Word("AKYLAS", "лучший певец"),
            Word("KUNI", "фанат куни"),
            Word("AEROSPACE", "аэрокос"),
            Word("PIROZHOK", "zxc пирог"),
            Word("GRISHA", "грiша"),
            Word("NIMESULID", "от чего?"),
            Word("CHETKI", "от деда"),
            Word("KASHAPOV", "пидорас"),
            Word("ADAPTATION", "адапт"),
            Word("MARTEEN", "семечки"),
            Word("SAMARA", "город возможностей"),
            Word("MADKID", "мэдк1д"),
            Word("CLUBROMANTIC", "клуб романтики")
        )
    )

    val minecraft = Theme(
        id = "minecraft",
        nameRu = "Майнкрафт",
        nameEn = "Minecraft",
        emoji = "⛏️",
        words = listOf(
            Word("MINECRAFT", "майнкрафт"),
            Word("CREEPER", "крипер"),
            Word("PICKAXE", "кирка"),
            Word("DIAMOND", "алмаз"),
            Word("ZOMBIE", "зомби"),
            Word("SKELETON", "скелет"),
            Word("VILLAGE", "деревня"),
            Word("PORTAL", "портал"),
            Word("TORCH", "факел"),
            Word("BLOCK", "блок"),
            Word("SWORD", "меч"),
            Word("APPLE", "яблоко"),
            Word("CAVE", "пещера"),
            Word("CRAFT", "крафт"),
            Word("SPAWN", "спавн"),
            Word("BIOME", "биом")
        )
    )

    val pirates = Theme(
        id = "pirates",
        nameRu = "Пираты",
        nameEn = "Pirates",
        emoji = "⚓",
        words = listOf(
            Word("PIRATE", "пират"),
            Word("TREASURE", "сокровище"),
            Word("COMPASS", "компас"),
            Word("ISLAND", "остров"),
            Word("PARROT", "попугай"),
            Word("CANNON", "пушка"),
            Word("ANCHOR", "якорь"),
            Word("SAIL", "парус"),
            Word("SHIP", "корабль"),
            Word("GOLD", "золото"),
            Word("MAP", "карта"),
            Word("CREW", "команда"),
            Word("SWORD", "меч"),
            Word("PLANK", "доска"),
            Word("CHEST", "сундук"),
            Word("RUM", "ром")
        )
    )

    val dinosaurs = Theme(
        id = "dinosaurs",
        nameRu = "Динозавры",
        nameEn = "Dinosaurs",
        emoji = "🦖",
        words = listOf(
            Word("RAPTOR", "раптор"),
            Word("FOSSIL", "окаменелость"),
            Word("JURASSIC", "юрский"),
            Word("EXTINCT", "вымерший"),
            Word("CLAW", "коготь"),
            Word("SCALE", "чешуя"),
            Word("HORN", "рог"),
            Word("NEST", "гнездо"),
            Word("EGG", "яйцо"),
            Word("TAIL", "хвост"),
            Word("ROAR", "рёв"),
            Word("BONE", "кость"),
            Word("MUSEUM", "музей"),
            Word("TRACK", "след"),
            Word("SPINE", "хребет"),
            Word("GIANT", "гигант")
        )
    )

    val magic = Theme(
        id = "magic",
        nameRu = "Магия",
        nameEn = "Magic",
        emoji = "✨",
        words = listOf(
            Word("WIZARD", "волшебник"),
            Word("WITCH", "ведьма"),
            Word("SPELL", "заклинание"),
            Word("MAGIC", "магия"),
            Word("WAND", "палочка"),
            Word("DRAGON", "дракон"),
            Word("FAIRY", "фея"),
            Word("CASTLE", "замок"),
            Word("CROWN", "корона"),
            Word("KNIGHT", "рыцарь"),
            Word("SWORD", "меч"),
            Word("SHIELD", "щит"),
            Word("TOWER", "башня"),
            Word("POTION", "зелье"),
            Word("CLOAK", "мантия"),
            Word("OWL", "сова")
        )
    )

    val heroes = Theme(
        id = "heroes",
        nameRu = "Супергерои",
        nameEn = "Superheroes",
        emoji = "🦸",
        words = listOf(
            Word("HERO", "герой"),
            Word("VILLAIN", "злодей"),
            Word("MASK", "маска"),
            Word("CAPE", "плащ"),
            Word("POWER", "сила"),
            Word("FLIGHT", "полёт"),
            Word("LASER", "лазер"),
            Word("SHIELD", "щит"),
            Word("ORIGIN", "происхождение"),
            Word("COMIC", "комикс"),
            Word("SIDEKICK", "напарник"),
            Word("RESCUE", "спасение"),
            Word("JUSTICE", "справедливость"),
            Word("LEGEND", "легенда"),
            Word("BATTLE", "битва"),
            Word("ARMOR", "броня")
        )
    )

    val dota = Theme(
        id = "dota",
        nameRu = "Дота",
        nameEn = "Dota",
        emoji = "🛡️",
        words = listOf(
            Word("DIMA", "дима"),
            Word("LANE", "полоса"),
            Word("CREEP", "крип"),
            Word("TOWER", "башня"),
            Word("ROSHAN", "Рошан"),
            Word("ITEM", "предмет"),
            Word("ABILITY", "способность"),
            Word("ULTIMATE", "ультимейт"),
            Word("GANK", "ганк"),
            Word("PUSH", "пуш"),
            Word("FARM", "фарм"),
            Word("STACK", "стак"),
            Word("SMOKE", "дымовая шашка"),
            Word("WARD", "варда"),
            Word("RUNE", "руна"),
            Word("TEAMFIGHT", "тимфайт")
        )
    )

    val mcdonalds = Theme(
        id = "mcdonalds",
        nameRu = "Макдональдс",
        nameEn = "McDonald's",
        emoji = "🍔",
        words = listOf(
            Word("BURGER", "бургер"),
            Word("FRIES", "картошка фри"),
            Word("NUGGETS", "наггетсы"),
            Word("MILKSHAKE", "молочный коктейль"),
            Word("SALAD", "салат"),
            Word("ROLL", "ролл"),
            Word("SAUCE", "соус"),
            Word("BREAKFAST", "завтрак"),
            Word("COFFEE", "кофе"),
            Word("DESSERT", "десерт"),
            Word("MENU", "меню"),
            Word("DRINKS", "напитки"),
            Word("TOY", "игрушка"),
            Word("PROMOTION", "акция"),
            Word("ORDER", "заказ"),
            Word("RESTAURANT", "ресторан")
        )
    )

    val tiktok = Theme(
        id = "tiktok",
        nameRu = "ТикТок",
        nameEn = "TikTok",
        emoji = "🎵",
        words = listOf(
            Word("VIDEO", "видео"),
            Word("TREND", "тренд"),
            Word("HASHTAG", "хэштег"),
            Word("CHALLENGE", "челлендж"),
            Word("DANCE", "танец"),
            Word("MUSIC", "музыка"),
            Word("FILTER", "фильтр"),
            Word("DUET", "дуэт"),
            Word("COMMENT", "комментарий"),
            Word("LIKE", "лайк"),
            Word("FOLLOWER", "подписчик"),
            Word("LIVE", "прямой эфир"),
            Word("STORY", "история"),
            Word("VIRAL", "вирусный"),
            Word("CONTENT", "контент"),
            Word("CREATOR", "создатель")
        )
    )

    val novosergievka = Theme(
        id = "novosergievka",
        nameRu = "Новосергиевка",
        nameEn = "Novosergievka",
        emoji = "🏡",
        words = listOf(
            Word("LOG", "бревно"),
            Word("FIVEOCHKA", "пятерочка"),
            Word("MALISHOK", "малышок"),
            Word("BATR", "бутримка"),
            Word("SCENE", "сцена"),
            Word("HATAVANI", "хата вани"),
            Word("CRAFT", "крафт"),
            Word("TOPOLYA", "тополя"),
            Word("VILLAGE", "село"),
            Word("KREMANKA", "креманка"),
            Word("SALUT", "салют"),
            Word("SOLOMKO", "соломко"),
            Word("LEBYAZHKA", "лебяжка"),
            Word("KOROVKA", "коровка"),
            Word("GTO", "гто"),
            Word("NOVATOR", "новатор"),
            Word("STADIUM", "стадион")
        )
    )

    val alcohol = Theme(
        id = "alcohol",
        nameRu = "Алкоголь",
        nameEn = "Alcohol",
        emoji = "🍷",
        words = listOf(
            Word("WINE", "вино"),
            Word("BEER", "пиво"),
            Word("VODKA", "водка"),
            Word("WHISKEY", "виски"),
            Word("RUM", "ром"),
            Word("GIN", "джин"),
            Word("TEQUILA", "текила"),
            Word("COCKTAIL", "коктейль"),
            Word("BRANDY", "бренди"),
            Word("CHAMPAGNE", "шампанское"),
            Word("LIQUEUR", "ликёр"),
            Word("APERITIF", "аперитив"),
            Word("DIGESTIF", "дижестив"),
            Word("FORTIFIED", "креплёное"),
            Word("MALT", "солод"),
            Word("YAGODA", "ягода")
        )
    )

    val cities = Theme(
        id = "cities",
        nameRu = "Города",
        nameEn = "Cities",
        emoji = "🏙️",
        words = listOf(
            Word("MOSCOW", "Москва"),
            Word("SAMARA", "Самара"),
            Word("ORENBURG", "Оренбург"),
            Word("VOLGOGRAD", "Волгоград"),
            Word("ORSK", "Орск"),
            Word("MAGADAN", "Магадан"),
            Word("MAIKOP", "Майкоп"),
            Word("BUZULUK", "Бузлук"),
            Word("NOVOTROICK", "Новотроицк"),
            Word("KAZAN", "Казань"),
            Word("TOLYATTI", "Тольятти"),
            Word("ZHIGULEVSK", "Жигулевск"),
            Word("SOLOMKINSK", "Соломкинск"),
            Word("NOVGOROD", "Новгород"),
            Word("KELN", "Кёльн"),
            Word("EKB", "Екатеринбург"),
            Word("TYUMEN", "Тюмень"),
            Word("SARATOV", "Саратов")
        )
    )

    val darya = Theme(
        id = "darya",
        nameRu = "Дарья",
        nameEn = "Darya",
        emoji = "💖",
        words = listOf(
            Word("BELOVED", "любимая"),
            Word("DARLING", "дорогая"),
            Word("SWEETHEART", "милая"),
            Word("PRINCESS", "принцесса"),
            Word("QUEEN", "королева"),
            Word("SUNSHINE", "солнышко"),
            Word("ANGEL", "ангел"),
            Word("TREASURE", "сокровище"),
            Word("LOVE", "любовь"),
            Word("DIAMOND", "бриллиант"),
            Word("GOLD", "золото"),
            Word("PEARL", "жемчужина"),
            Word("THEBEST", "самая лучшая"),
            Word("MARRIED", "замужем"),
            Word("HAPPINESS", "счастье"),
            Word("FOREVER", "навсегда")
        )
    )

    val aerospace = Theme(
        id = "aerospace",
        nameRu = "Аэрокос",
        nameEn = "Aerospace",
        emoji = "🚀",
        words = listOf(
            Word("SLUTOV", "шлюха д.г."),
            Word("SAMSONOVA", "самсонова л.с."),
            Word("KURAPOVA", "курапова е.с."),
            Word("SHUKMAN", "шукман в.э."),
            Word("SILANTIEVA", "силантьева д.б."),
            Word("AMIRZANOVA", "амирзанова а.р."),
            Word("DOLGOZVYAGO", "долгозвяго а.а."),
            Word("NEKRUZ", "некруз"),
            Word("GAIDEL", "гайдель а.в."),
            Word("YABLOKOVA", "яблокова л.в."),
            Word("ZHURAVLEV", "журавлев"),
            Word("PANOV", "панов"),
            Word("RUDMAN", "рудман р.м."),
            Word("MINETOV", "минетов а.а."),
            Word("ZAKHAROV", "захаров н."),
            Word("KASHAPOV", "кашапов а.и."),
            Word("FEDULOV", "федулов м.")
        )
    )

    val seasons = Theme(
        id = "seasons",
        nameRu = "Времена года",
        nameEn = "Seasons",
        emoji = "🌦️",
        words = listOf(
            Word("SPRING", "весна"),
            Word("SUMMER", "лето"),
            Word("AUTUMN", "осень"),
            Word("WINTER", "зима"),
            Word("SNOW", "снег"),
            Word("RAIN", "дождь"),
            Word("SUN", "солнце"),
            Word("LEAF", "лист"),
            Word("WARM", "тепло"),
            Word("COLD", "холод"),
            Word("MARCH", "март"),
            Word("JUNE", "июнь"),
            Word("JULY", "июль"),
            Word("AUGUST", "август"),
            Word("DECEMBER", "декабрь"),
            Word("FLOWER", "цветок")
        )
    )

    val holidays = Theme(
        id = "holidays",
        nameRu = "Праздники",
        nameEn = "Holidays",
        emoji = "🎉",
        words = listOf(
            Word("HOLIDAY", "праздник"),
            Word("PARTY", "вечеринка"),
            Word("GIFT", "подарок"),
            Word("CAKE", "торт"),
            Word("BALLOON", "шарик"),
            Word("CANDLE", "свеча"),
            Word("GUEST", "гость"),
            Word("PRESENT", "презент"),
            Word("CONFETTI", "конфетти"),
            Word("FIREWORK", "фейерверк"),
            Word("PARADE", "парад"),
            Word("GREETING", "поздравление"),
            Word("FESTIVAL", "фестиваль"),
            Word("CLOWN", "клоун"),
            Word("MUSIC", "музыка"),
            Word("DANCE", "танец")
        )
    )

    val circus = Theme(
        id = "circus",
        nameRu = "Цирк",
        nameEn = "Circus",
        emoji = "🎪",
        words = listOf(
            Word("CIRCUS", "цирк"),
            Word("CLOWN", "клоун"),
            Word("TENT", "шатёр"),
            Word("ACROBAT", "акробат"),
            Word("JUGGLER", "жонглёр"),
            Word("MAGICIAN", "фокусник"),
            Word("LION", "лев"),
            Word("TIGER", "тигр"),
            Word("ELEPHANT", "слон"),
            Word("TRAPEZE", "трапеция"),
            Word("RING", "арена"),
            Word("POPCORN", "попкорн"),
            Word("TICKET", "билет"),
            Word("PARADE", "парад"),
            Word("DRUM", "барабан"),
            Word("ROPE", "канат")
        )
    )

    val zoo = Theme(
        id = "zoo",
        nameRu = "Зоопарк",
        nameEn = "Zoo",
        emoji = "🦁",
        words = listOf(
            Word("ZOO", "зоопарк"),
            Word("LION", "лев"),
            Word("TIGER", "тигр"),
            Word("MONKEY", "обезьяна"),
            Word("GIRAFFE", "жираф"),
            Word("ZEBRA", "зебра"),
            Word("PANDA", "панда"),
            Word("KOALA", "коала"),
            Word("CAMEL", "верблюд"),
            Word("HIPPO", "бегемот"),
            Word("RHINO", "носорог"),
            Word("SNAKE", "змея"),
            Word("PENGUIN", "пингвин"),
            Word("KANGAROO", "кенгуру"),
            Word("CROCODILE", "крокодил"),
            Word("OSTRICH", "страус")
        )
    )

    val egypt = Theme(
        id = "egypt",
        nameRu = "Древний Египет",
        nameEn = "Ancient Egypt",
        emoji = "🐪",
        words = listOf(
            Word("PYRAMID", "пирамида"),
            Word("PHARAOH", "фараон"),
            Word("SPHINX", "сфинкс"),
            Word("MUMMY", "мумия"),
            Word("CAMEL", "верблюд"),
            Word("NILE", "Нил"),
            Word("DESERT", "пустыня"),
            Word("SCARAB", "скарабей"),
            Word("PAPYRUS", "папирус"),
            Word("TEMPLE", "храм"),
            Word("TOMB", "гробница"),
            Word("SAND", "песок"),
            Word("GOLD", "золото"),
            Word("ANUBIS", "Анубис"),
            Word("HIEROGLYPH", "иероглиф"),
            Word("OBELISK", "обелиск")
        )
    )

    val rome = Theme(
        id = "rome",
        nameRu = "Древний Рим",
        nameEn = "Ancient Rome",
        emoji = "🏛️",
        words = listOf(
            Word("ROME", "Рим"),
            Word("ROMAN", "римлянин"),
            Word("EMPEROR", "император"),
            Word("CAESAR", "Цезарь"),
            Word("LEGION", "легион"),
            Word("GLADIATOR", "гладиатор"),
            Word("ARENA", "арена"),
            Word("COLOSSEUM", "Колизей"),
            Word("TOGA", "тога"),
            Word("SENATE", "сенат"),
            Word("TEMPLE", "храм"),
            Word("AQUEDUCT", "акведук"),
            Word("MOSAIC", "мозаика"),
            Word("LATIN", "латынь"),
            Word("CHARIOT", "колесница"),
            Word("LAUREL", "лавр")
        )
    )

    val vikings = Theme(
        id = "vikings",
        nameRu = "Викинги",
        nameEn = "Vikings",
        emoji = "⚔️",
        words = listOf(
            Word("VIKING", "викинг"),
            Word("LONGSHIP", "драккар"),
            Word("WARRIOR", "воин"),
            Word("SHIELD", "щит"),
            Word("AXE", "топор"),
            Word("HELMET", "шлем"),
            Word("RUNE", "руна"),
            Word("NORSE", "скандинав"),
            Word("RAID", "набег"),
            Word("FJORD", "фьорд"),
            Word("SAGA", "сага"),
            Word("THOR", "Тор"),
            Word("ODIN", "Один"),
            Word("VALHALLA", "Вальхалла"),
            Word("DRAGON", "дракон"),
            Word("SAIL", "парус")
        )
    )

    val samurai = Theme(
        id = "samurai",
        nameRu = "Самураи",
        nameEn = "Samurai",
        emoji = "🏯",
        words = listOf(
            Word("SAMURAI", "самурай"),
            Word("KATANA", "катана"),
            Word("SWORD", "меч"),
            Word("ARMOR", "доспехи"),
            Word("HELMET", "шлем"),
            Word("SHOGUN", "сёгун"),
            Word("SENSEI", "учитель"),
            Word("DOJO", "додзё"),
            Word("HONOR", "честь"),
            Word("BOW", "лук"),
            Word("ARROW", "стрела"),
            Word("CASTLE", "замок"),
            Word("EMPEROR", "император"),
            Word("JAPAN", "Япония"),
            Word("CHERRY", "сакура"),
            Word("TEA", "чай")
        )
    )

    val ninja = Theme(
        id = "ninja",
        nameRu = "Ниндзя",
        nameEn = "Ninja",
        emoji = "🥷",
        words = listOf(
            Word("NINJA", "ниндзя"),
            Word("SHURIKEN", "сюрикен"),
            Word("KATANA", "катана"),
            Word("STEALTH", "скрытность"),
            Word("SHADOW", "тень"),
            Word("SMOKE", "дым"),
            Word("SILENT", "бесшумный"),
            Word("CLIMB", "лазать"),
            Word("ROOF", "крыша"),
            Word("MASK", "маска"),
            Word("DARK", "тьма"),
            Word("SWIFT", "быстрый"),
            Word("DAGGER", "кинжал"),
            Word("SCROLL", "свиток"),
            Word("DISGUISE", "маскировка"),
            Word("ESCAPE", "побег")
        )
    )

    val cowboys = Theme(
        id = "cowboys",
        nameRu = "Ковбои",
        nameEn = "Cowboys",
        emoji = "🤠",
        words = listOf(
            Word("COWBOY", "ковбой"),
            Word("HORSE", "лошадь"),
            Word("SADDLE", "седло"),
            Word("LASSO", "лассо"),
            Word("RANCH", "ранчо"),
            Word("DESERT", "пустыня"),
            Word("CACTUS", "кактус"),
            Word("BOOTS", "сапоги"),
            Word("HAT", "шляпа"),
            Word("SPURS", "шпоры"),
            Word("TRAIL", "тропа"),
            Word("BISON", "бизон"),
            Word("FIRE", "костёр"),
            Word("DUST", "пыль"),
            Word("SALOON", "салун"),
            Word("GUITAR", "гитара")
        )
    )

    val detectives = Theme(
        id = "detectives",
        nameRu = "Детективы",
        nameEn = "Detectives",
        emoji = "🕵️",
        words = listOf(
            Word("DETECTIVE", "детектив"),
            Word("CLUE", "улика"),
            Word("CRIME", "преступление"),
            Word("SUSPECT", "подозреваемый"),
            Word("WITNESS", "свидетель"),
            Word("CASE", "дело"),
            Word("EVIDENCE", "доказательство"),
            Word("ALIBI", "алиби"),
            Word("MURDER", "убийство"),
            Word("MYSTERY", "тайна"),
            Word("POLICE", "полиция"),
            Word("AGENT", "агент"),
            Word("SECRET", "секрет"),
            Word("SHADOW", "тень"),
            Word("TRAIL", "след"),
            Word("LENS", "линза")
        )
    )

    val spies = Theme(
        id = "spies",
        nameRu = "Шпионы",
        nameEn = "Spies",
        emoji = "🕶️",
        words = listOf(
            Word("SPY", "шпион"),
            Word("AGENT", "агент"),
            Word("SECRET", "секрет"),
            Word("CODE", "код"),
            Word("CIPHER", "шифр"),
            Word("GADGET", "гаджет"),
            Word("CAMERA", "камера"),
            Word("LASER", "лазер"),
            Word("MISSION", "миссия"),
            Word("ENEMY", "враг"),
            Word("DISGUISE", "маскировка"),
            Word("SILENT", "тихий"),
            Word("TARGET", "цель"),
            Word("ESCAPE", "побег"),
            Word("BRIEFCASE", "портфель"),
            Word("RADIO", "рация")
        )
    )

    val firefighters = Theme(
        id = "firefighters",
        nameRu = "Пожарные",
        nameEn = "Firefighters",
        emoji = "🚒",
        words = listOf(
            Word("FIRE", "огонь"),
            Word("TRUCK", "машина"),
            Word("HOSE", "рукав"),
            Word("LADDER", "лестница"),
            Word("HELMET", "каска"),
            Word("SMOKE", "дым"),
            Word("FLAME", "пламя"),
            Word("RESCUE", "спасение"),
            Word("BRAVE", "смелый"),
            Word("ALARM", "тревога"),
            Word("STATION", "часть"),
            Word("ENGINE", "насос"),
            Word("WATER", "вода"),
            Word("AXE", "топор"),
            Word("MASK", "маска"),
            Word("TEAM", "команда")
        )
    )

    val police = Theme(
        id = "police",
        nameRu = "Полиция",
        nameEn = "Police",
        emoji = "🚓",
        words = listOf(
            Word("POLICE", "полиция"),
            Word("OFFICER", "офицер"),
            Word("BADGE", "значок"),
            Word("PATROL", "патруль"),
            Word("CRIME", "преступление"),
            Word("LAW", "закон"),
            Word("JUSTICE", "правосудие"),
            Word("ARREST", "арест"),
            Word("CHASE", "погоня"),
            Word("SIREN", "сирена"),
            Word("UNIFORM", "форма"),
            Word("SAFE", "безопасно"),
            Word("GUARD", "охрана"),
            Word("CASE", "дело"),
            Word("CLUE", "улика"),
            Word("COURT", "суд")
        )
    )

    val construction = Theme(
        id = "construction",
        nameRu = "Стройка",
        nameEn = "Construction",
        emoji = "🏗️",
        words = listOf(
            Word("BUILD", "строить"),
            Word("CRANE", "кран"),
            Word("BRICK", "кирпич"),
            Word("CEMENT", "цемент"),
            Word("STEEL", "сталь"),
            Word("WORKER", "рабочий"),
            Word("HELMET", "каска"),
            Word("TOOL", "инструмент"),
            Word("DRILL", "дрель"),
            Word("NAIL", "гвоздь"),
            Word("HAMMER", "молоток"),
            Word("TRUCK", "грузовик"),
            Word("PLAN", "план"),
            Word("SITE", "площадка"),
            Word("TOWER", "башня"),
            Word("FENCE", "забор")
        )
    )

    val supermarket = Theme(
        id = "supermarket",
        nameRu = "Супермаркет",
        nameEn = "Supermarket",
        emoji = "🛒",
        words = listOf(
            Word("SHOP", "магазин"),
            Word("CART", "тележка"),
            Word("BASKET", "корзина"),
            Word("SHELF", "полка"),
            Word("PRICE", "цена"),
            Word("CASHIER", "кассир"),
            Word("RECEIPT", "чек"),
            Word("QUEUE", "очередь"),
            Word("PRODUCT", "продукт"),
            Word("DISCOUNT", "скидка"),
            Word("AISLE", "проход"),
            Word("TILL", "касса"),
            Word("BAG", "пакет"),
            Word("BRAND", "бренд"),
            Word("STOCK", "запас"),
            Word("CUSTOMER", "покупатель")
        )
    )

    val cafe = Theme(
        id = "cafe",
        nameRu = "Кафе",
        nameEn = "Cafe",
        emoji = "☕",
        words = listOf(
            Word("CAFE", "кафе"),
            Word("COFFEE", "кофе"),
            Word("TEA", "чай"),
            Word("LATTE", "латте"),
            Word("CAPPUCCINO", "капучино"),
            Word("MUFFIN", "маффин"),
            Word("CROISSANT", "круассан"),
            Word("WAITER", "официант"),
            Word("MENU", "меню"),
            Word("TABLE", "столик"),
            Word("CUP", "чашка"),
            Word("SAUCER", "блюдце"),
            Word("SUGAR", "сахар"),
            Word("DESSERT", "десерт"),
            Word("BILL", "счёт"),
            Word("TIP", "чаевые")
        )
    )

    val birthday = Theme(
        id = "birthday",
        nameRu = "День рождения",
        nameEn = "Birthday",
        emoji = "🎂",
        words = listOf(
            Word("BIRTHDAY", "день рождения"),
            Word("PARTY", "вечеринка"),
            Word("CAKE", "торт"),
            Word("CANDLE", "свеча"),
            Word("GIFT", "подарок"),
            Word("BALLOON", "шарик"),
            Word("GUEST", "гость"),
            Word("WISH", "желание"),
            Word("CARD", "открытка"),
            Word("PRESENT", "подарок"),
            Word("MUSIC", "музыка"),
            Word("GAMES", "игры"),
            Word("CANDY", "конфета"),
            Word("CLOWN", "клоун"),
            Word("INVITATION", "приглашение"),
            Word("SURPRISE", "сюрприз")
        )
    )

    val wedding = Theme(
        id = "wedding",
        nameRu = "Свадьба",
        nameEn = "Wedding",
        emoji = "💍",
        words = listOf(
            Word("WEDDING", "свадьба"),
            Word("BRIDE", "невеста"),
            Word("GROOM", "жених"),
            Word("RING", "кольцо"),
            Word("DRESS", "платье"),
            Word("FLOWERS", "цветы"),
            Word("CAKE", "торт"),
            Word("GUEST", "гость"),
            Word("VOWS", "клятвы"),
            Word("MUSIC", "музыка"),
            Word("DANCE", "танец"),
            Word("PHOTO", "фото"),
            Word("CHURCH", "церковь"),
            Word("PARTY", "вечеринка"),
            Word("LOVE", "любовь"),
            Word("KISS", "поцелуй")
        )
    )

    val christmas = Theme(
        id = "christmas",
        nameRu = "Рождество",
        nameEn = "Christmas",
        emoji = "🎄",
        words = listOf(
            Word("CHRISTMAS", "Рождество"),
            Word("SANTA", "Санта"),
            Word("ELF", "эльф"),
            Word("REINDEER", "олень"),
            Word("SLEIGH", "сани"),
            Word("SNOW", "снег"),
            Word("GIFT", "подарок"),
            Word("TREE", "ёлка"),
            Word("STAR", "звезда"),
            Word("BELL", "колокольчик"),
            Word("CANDLE", "свеча"),
            Word("COOKIE", "печенье"),
            Word("STOCKING", "носок"),
            Word("SNOWMAN", "снеговик"),
            Word("CAROL", "гимн"),
            Word("MISTLETOE", "омела")
        )
    )

    val planets = Theme(
        id = "planets",
        nameRu = "Планеты",
        nameEn = "Planets",
        emoji = "🌍",
        words = listOf(
            Word("PLANET", "планета"),
            Word("MERCURY", "Меркурий"),
            Word("VENUS", "Венера"),
            Word("EARTH", "Земля"),
            Word("MARS", "Марс"),
            Word("JUPITER", "Юпитер"),
            Word("SATURN", "Сатурн"),
            Word("URANUS", "Уран"),
            Word("NEPTUNE", "Нептун"),
            Word("MOON", "Луна"),
            Word("SUN", "Солнце"),
            Word("STAR", "звезда"),
            Word("ORBIT", "орбита"),
            Word("COMET", "комета"),
            Word("ROCKET", "ракета"),
            Word("GALAXY", "галактика")
        )
    )

    val robots = Theme(
        id = "robots",
        nameRu = "Роботы",
        nameEn = "Robots",
        emoji = "🤖",
        words = listOf(
            Word("ROBOT", "робот"),
            Word("ANDROID", "андроид"),
            Word("CIRCUIT", "схема"),
            Word("SENSOR", "датчик"),
            Word("MOTOR", "мотор"),
            Word("BATTERY", "батарея"),
            Word("METAL", "металл"),
            Word("WIRE", "провод"),
            Word("GEAR", "шестерня"),
            Word("CODE", "код"),
            Word("PROGRAM", "программа"),
            Word("MACHINE", "машина"),
            Word("LASER", "лазер"),
            Word("DRONE", "дрон"),
            Word("CHIP", "чип"),
            Word("LOGIC", "логика")
        )
    )

    val internet = Theme(
        id = "internet",
        nameRu = "Интернет",
        nameEn = "Internet",
        emoji = "🌐",
        words = listOf(
            Word("INTERNET", "интернет"),
            Word("WEBSITE", "сайт"),
            Word("BROWSER", "браузер"),
            Word("EMAIL", "почта"),
            Word("ONLINE", "онлайн"),
            Word("LINK", "ссылка"),
            Word("CLICK", "клик"),
            Word("LOGIN", "вход"),
            Word("PASSWORD", "пароль"),
            Word("SEARCH", "поиск"),
            Word("SERVER", "сервер"),
            Word("DOMAIN", "домен"),
            Word("DOWNLOAD", "скачать"),
            Word("UPLOAD", "загрузить"),
            Word("CHAT", "чат"),
            Word("WIFI", "вайфай")
        )
    )

    val books = Theme(
        id = "books",
        nameRu = "Книги",
        nameEn = "Books",
        emoji = "📚",
        words = listOf(
            Word("BOOK", "книга"),
            Word("PAGE", "страница"),
            Word("STORY", "история"),
            Word("NOVEL", "роман"),
            Word("AUTHOR", "автор"),
            Word("PLOT", "сюжет"),
            Word("HERO", "герой"),
            Word("CHAPTER", "глава"),
            Word("LIBRARY", "библиотека"),
            Word("SHELF", "полка"),
            Word("READ", "читать"),
            Word("TITLE", "название"),
            Word("POEM", "стих"),
            Word("FABLE", "басня"),
            Word("LEGEND", "легенда"),
            Word("INK", "чернила")
        )
    )

    val theatre = Theme(
        id = "theatre",
        nameRu = "Театр",
        nameEn = "Theatre",
        emoji = "🎭",
        words = listOf(
            Word("THEATRE", "театр"),
            Word("STAGE", "сцена"),
            Word("ACTOR", "актёр"),
            Word("ROLE", "роль"),
            Word("SCENE", "сцена"),
            Word("COSTUME", "костюм"),
            Word("MASK", "маска"),
            Word("DRAMA", "драма"),
            Word("COMEDY", "комедия"),
            Word("TRAGEDY", "трагедия"),
            Word("CURTAIN", "занавес"),
            Word("TICKET", "билет"),
            Word("AUDIENCE", "зрители"),
            Word("SCRIPT", "сценарий"),
            Word("LIGHT", "свет"),
            Word("BOW", "поклон")
        )
    )

    val cinema = Theme(
        id = "cinema",
        nameRu = "Кино",
        nameEn = "Cinema",
        emoji = "🎬",
        words = listOf(
            Word("MOVIE", "фильм"),
            Word("FILM", "кино"),
            Word("ACTOR", "актёр"),
            Word("DIRECTOR", "режиссёр"),
            Word("CAMERA", "камера"),
            Word("SCENE", "сцена"),
            Word("SCRIPT", "сценарий"),
            Word("OSCAR", "Оскар"),
            Word("POPCORN", "попкорн"),
            Word("TICKET", "билет"),
            Word("SCREEN", "экран"),
            Word("TRAILER", "трейлер"),
            Word("COMEDY", "комедия"),
            Word("DRAMA", "драма"),
            Word("HERO", "герой"),
            Word("STAR", "звезда")
        )
    )

    val football = Theme(
        id = "football",
        nameRu = "Футбол",
        nameEn = "Football",
        emoji = "⚽",
        words = listOf(
            Word("GOAL", "гол"),
            Word("BALL", "мяч"),
            Word("MATCH", "матч"),
            Word("TEAM", "команда"),
            Word("COACH", "тренер"),
            Word("REFEREE", "судья"),
            Word("STADIUM", "стадион"),
            Word("TROPHY", "трофей"),
            Word("SCORE", "счёт"),
            Word("PENALTY", "пенальти"),
            Word("OFFSIDE", "офсайд"),
            Word("CORNER", "угловой"),
            Word("KEEPER", "вратарь"),
            Word("DRIBBLE", "дриблинг"),
            Word("HEADER", "удар головой"),
            Word("FAN", "болельщик")
        )
    )

    val chess = Theme(
        id = "chess",
        nameRu = "Шахматы",
        nameEn = "Chess",
        emoji = "♟️",
        words = listOf(
            Word("CHESS", "шахматы"),
            Word("BOARD", "доска"),
            Word("PIECE", "фигура"),
            Word("KING", "король"),
            Word("QUEEN", "королева"),
            Word("ROOK", "ладья"),
            Word("BISHOP", "слон"),
            Word("KNIGHT", "конь"),
            Word("PAWN", "пешка"),
            Word("MOVE", "ход"),
            Word("CHECK", "шах"),
            Word("MATE", "мат"),
            Word("CASTLE", "рокировка"),
            Word("ATTACK", "атака"),
            Word("DEFENSE", "защита"),
            Word("DRAW", "ничья")
        )
    )

    val desert = Theme(
        id = "desert",
        nameRu = "Пустыня",
        nameEn = "Desert",
        emoji = "🏜️",
        words = listOf(
            Word("DESERT", "пустыня"),
            Word("SAND", "песок"),
            Word("DUNE", "дюна"),
            Word("CACTUS", "кактус"),
            Word("CAMEL", "верблюд"),
            Word("OASIS", "оазис"),
            Word("HEAT", "жара"),
            Word("SUN", "солнце"),
            Word("DRY", "сухо"),
            Word("MIRAGE", "мираж"),
            Word("SCORPION", "скорпион"),
            Word("LIZARD", "ящерица"),
            Word("SNAKE", "змея"),
            Word("PALM", "пальма"),
            Word("CARAVAN", "караван"),
            Word("DUST", "пыль")
        )
    )

    val mythical = Theme(
        id = "mythical",
        nameRu = "Мифические существа",
        nameEn = "Mythical Creatures",
        emoji = "🐉",
        words = listOf(
            Word("DRAGON", "дракон"),
            Word("UNICORN", "единорог"),
            Word("MERMAID", "русалка"),
            Word("PHOENIX", "феникс"),
            Word("GRIFFIN", "грифон"),
            Word("CENTAUR", "кентавр"),
            Word("MINOTAUR", "минотавр"),
            Word("CYCLOPS", "циклоп"),
            Word("TITAN", "титан"),
            Word("NYMPH", "нимфа"),
            Word("FAIRY", "фея"),
            Word("GIANT", "великан"),
            Word("TROLL", "тролль"),
            Word("GOBLIN", "гоблин"),
            Word("WEREWOLF", "оборотень"),
            Word("PEGASUS", "пегас")
        )
    )

    /** Отдельный праздничный уровень, не входит в кампании. */
    val halloween = Theme(
        id = "halloween",
        nameRu = "Хеллоуин",
        nameEn = "Halloween",
        emoji = "🎃",
        words = listOf(
            Word("PUMPKIN", "тыква"),
            Word("GHOST", "призрак"),
            Word("WITCH", "ведьма"),
            Word("SPIDER", "паук"),
            Word("BAT", "летучая мышь"),
            Word("CANDLE", "свеча"),
            Word("SKELETON", "скелет"),
            Word("VAMPIRE", "вампир"),
            Word("ZOMBIE", "зомби"),
            Word("WEREWOLF", "оборотень"),
            Word("COFFIN", "гроб"),
            Word("POTION", "зелье"),
            Word("CAULDRON", "котёл"),
            Word("MUMMY", "мумия"),
            Word("GRAVE", "могила"),
            Word("CANDY", "конфета")
        )
    )

    /** Порядок тем совпадает с порядком уровней внутри каждой сложности. */
    val all: List<Theme> = listOf(
        trees, animals, food, travel, space, music, ocean, flowers,
        weather, clothes, body, house, school, sport, transport, insects, desserts, professions,
        fruits, vegetables, colors, shapes, kitchen, tools, birds, fish, toys, feelings,
        drinks, bakery, furniture, bathroom, garden, farm, mountains, city, medicine, technology,
        brawlstars, myself, brawlers, brawlers2, brawlers3, localdarya,
        minecraft, pirates, dinosaurs, magic, heroes, dota, mcdonalds, tiktok, novosergievka,
        alcohol, cities, darya, aerospace,
        seasons, holidays, circus, zoo, egypt, rome, vikings, samurai, ninja, cowboys,
        detectives, spies, firefighters, police, construction, supermarket, cafe, birthday,
        wedding, christmas, planets, robots, internet, books, theatre, cinema, football, chess,
        desert, mythical
    )
}
